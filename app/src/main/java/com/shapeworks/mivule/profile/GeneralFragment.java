package com.shapeworks.mivule.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shapeworks.mivule.Controls.Firebase;
import com.shapeworks.mivule.R;
import com.google.firebase.auth.FirebaseAuth;


public class GeneralFragment extends Fragment {

    private TextView mUsername;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public GeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_general, container, false);
//        mUsername = rootView.findViewById(R.id.pfl_account_holder);
//        mUsername.setText(mAuth.getCurrentUser().getEmail());
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
