package com.shapeworks.mivule.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shapeworks.mivule.Controls.Firebase;
import com.shapeworks.mivule.R;
import com.shapeworks.mivule.profile.GeneralFragment;
import com.shapeworks.mivule.profile.HiddenFragment;
import com.shapeworks.mivule.subfragments.consult;
import com.shapeworks.mivule.subfragments.forums;
import com.shapeworks.mivule.ui.dialogs.*;
import com.shapeworks.mivule.ui.dialogs.PflSecFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;


public class ProfileFragment extends Fragment {

    private TextView tt_ext;
    private Context context;
    private DialogManager securityDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
//        tt_ext = rootView.findViewById(R.id.capital_digit);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                rootView.findViewById(R.id.profile_bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.action_item2:
                                GeneralFragment general = new GeneralFragment();
                                selectedFragment = general;
                                break;
                            case R.id.action_item3:

                                HiddenFragment hidden = new HiddenFragment();
                                if(showDialog()){
                                    selectedFragment = hidden;
                                }else
                                    selectedFragment = new NullFragment();
                                break;
                        }

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.profile_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        GeneralFragment general = new GeneralFragment();
        transaction.replace(R.id.profile_layout, general);
        transaction.commit();
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        return rootView;
    }

    public Boolean showDialog() {
        // Create the fragment and show it as a dialog.
        PflSecFragment newFragment = new PflSecFragment();
        //FragmentTransaction trans = getFragmentManager().beginTransaction();
        newFragment.show(getFragmentManager(), "dialog");
        //trans.hide(fragment);

        //======================BE SURE TO ADD SOME LOGIC HERE TO VERIFY THE SECURITY PIN ===================//

        DocumentReference userRef = db.collection("users").document(mAuth.getUid());
        if(userRef == null){
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
//                        if(mAuth.getUid() == document.getId()){
//                            Log.d(TAG, "you can now check private details");
//                        }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            return true;

        }else{
            Log.d(TAG, "you don't have permission");
            return false;
        }

    }

}
