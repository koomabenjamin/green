package com.shapeworks.mivule.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shapeworks.mivule.R;
import com.shapeworks.mivule.fragments.NullFragment;
import com.shapeworks.mivule.profile.HiddenFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class PflSecFragment extends DialogFragment {

    private Button accessBtn;
    public EditText mSecurityPin;
    private Context context = getContext();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DocumentReference securityPinRef = db.collection("users").document(mAuth.getUid());
    public PflSecFragment() {
        // Required empty public constructor
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.fragment_pfl_sec, null));
//
////                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int id) { // sign in the user ...
////                    } })
////                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////
////                        PflSecFragment.this.getDialog().cancel(); }
////            });
//
//        return builder.create();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_pfl_sec, container, false);
        mSecurityPin = rootView.findViewById(R.id.securityPin);
        accessBtn = (Button) rootView.findViewById(R.id.accessBtn);

       //DocumentReference securityPinRef = db.collection("users").document(mAuth.getUid());

        accessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSecurity();
            }
        });

        return rootView;
    }

    private void checkSecurity(){
        final String pin = mSecurityPin.getText().toString();
        if(pin.isEmpty()){

            android.support.v4.app.Fragment hidden = new NullFragment();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            Toast.makeText(getContext(), "security is not filled in", Toast.LENGTH_SHORT).show();
            trans.replace(R.id.profile_layout, hidden);
            trans.commit();
        }else {

            securityPinRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
                            String pini = task.getResult().getString("securityPin");
                            if(pini == pin){
                                android.support.v4.app.Fragment hidden = new HiddenFragment();
                                FragmentTransaction trans = getFragmentManager().beginTransaction();
                                trans.replace(R.id.profile_layout, hidden);
                                trans.commit();
                                Toast.makeText(getContext(), "Access Granted", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            android.support.v4.app.Fragment hidden = new NullFragment();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.profile_layout, hidden);
            trans.commit();
            Toast.makeText(getContext(), "Access Denied", Toast.LENGTH_SHORT).show();

        }
    }
}
