package com.shapeworks.mivule.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shapeworks.mivule.R;
import com.google.firebase.auth.FirebaseAuth;


public class HiddenFragment extends Fragment {

    private TextView mUsername;
    private Button mShowBtn;
    private Button mHideBtn;
    private CardView mhiddenCardview;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public HiddenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hidden, container, false);

        mShowBtn = rootView.findViewById(R.id.btn_show);
        mHideBtn = rootView.findViewById(R.id.btn_hide);
        mhiddenCardview = rootView.findViewById(R.id.hiddenCardView);
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhiddenCardview.setVisibility(View.VISIBLE);
            }
        });

        mHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mhiddenCardview.setVisibility(View.GONE);
            }
        });

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
