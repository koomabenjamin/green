package com.shapeworks.mivule.subfragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.shapeworks.mivule.Adapters.ConsultAdapter;
import com.shapeworks.mivule.Adapters.NewsAdapter;
import com.shapeworks.mivule.Entities.Articles;
import com.shapeworks.mivule.Entities.Consultant;
import com.shapeworks.mivule.Entities.Newsfeed;
import com.shapeworks.mivule.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.shapeworks.mivule.SettingsActivity;
import com.shapeworks.mivule.detail.d_consult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.id.list;


public class consult extends Fragment implements
        ConsultAdapter.OnConsultSelectedListener {


    private RecyclerView mRecycler;
    private ConsultAdapter mAdapter, sAdapter;
    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
//    private StorageReference mStorageRef =  mStorage.getReference().child("content/images/kyakachwa/01.png");
//    private StorageReference mStorageRef0 =  mStorage.getReference().child("content/images/kyakachwa/02.png");
//    private StorageReference mStorageRef1=  mStorage.getReference().child("content/images/kyakachwa/03.png");
//    private StorageReference mStorageRef2 =  mStorage.getReference().child("content/images/kyakachwa/04.png");

    private static final String TAG = "Projects";
    private static final int RC_SIGN_IN = 9001;
    private static final int LIMIT = 50;
    private FirebaseFirestore mFirestore;
    private Query mQuery;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewGroup mEmptyView;


    public consult() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_consult, container, false);
        progressBar = rootView.findViewById(R.id.progress_loading);
        progressBar.setVisibility(View.VISIBLE);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler = rootView.findViewById(R.id.consult_recycler);
        mEmptyView = rootView.findViewById(R.id.view_empty);

        FirebaseFirestore.setLoggingEnabled(true);
        initFirestore();
        initRecyclerView();

        return rootView;
    }


    private void initFirestore() {
        // TODO(developer): Implement
        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("consultants")
                .orderBy("name", Query.Direction.DESCENDING)
                .limit(LIMIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_consult, menu);
        super.onCreateOptionsMenu(menu,inflater);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings){

            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new ConsultAdapter(mQuery, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
//                Snackbar.make(findViewById(android.R.id.content),
//                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
                Log.d(TAG, "error error error");
            }
        };

//        if(mProjectRecycler != null){
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
//        }else{
//
//        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // Start sign in if necessary
//        if (shouldStartSignIn()) {
//            startSignIn();
//            return;
//        }

        // Start listening for Firestore updates
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }



    private boolean shouldStartSignIn() {
//        return (!mViewModel.getIsSigningIn() && FirebaseAuth.getInstance().getCurrentUser() == null);
        return true;
    }

    private void startSignIn() {
        // Sign in with FirebaseUI
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
        //mViewModel.setIsSigningIn(true);
    }

    @Override
    public void onConsultSelected(DocumentSnapshot restaurant) {
        alertDialog(restaurant);
    }

    public void alertDialog(final DocumentSnapshot snap){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
         builder.setTitle(snap.toObject(Consultant.class).getName())
                .setMessage(snap.toObject(Consultant.class).getEmail())
                //.setView(R.layout.fragment_pfl_sec)
                .setPositiveButton("Consult", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getContext(), d_consult.class);
                        i.putExtra("email", snap.toObject(Consultant.class).getEmail());
                        i.putExtra("name", snap.toObject(Consultant.class).getName());
                        startActivity(i);
                    }
                })

                //.setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}





//public class consult extends Fragment {
//
//    private RecyclerView mRecycler;
//    private ConsultAdapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//    private List<Consultant> list = new ArrayList<>();
//    private RatingBar mRatingBar;
//
//    public consult() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        int max = 5;
////        mRatingBar.setMax(5);
////        mRatingBar.setNumStars(max);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        mLayoutManager = new LinearLayoutManager(getContext());
////        mRecycler.setLayoutManager(mlayoutManager);
////        mRecycler.setItemAnimator(new DefaultItemAnimator());
////        mRecycler.setAdapter(mAdapter);
//        View rootView = inflater.inflate(R.layout.fragment_consult, container, false);
//        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler_consult);
//        mRecycler.setLayoutManager(mLayoutManager);
//        prepareConsultantData();
//        mAdapter = new ConsultAdapter(list);
//        mRecycler.setAdapter(mAdapter);
//
//        return rootView;
//    }
//
//    private void prepareConsultantData() {
//
////        int img_url = R.mipmap.mivule;
////        String brief = "with more than 30 years of experience and world class know, surely am a good fit";
////        String title = "Mahogany Researcher";
////
////        Consultant article = new Consultant("Kooma daughter", title, brief, img_url);
////        list.add(article);
////
////        article = new Consultant("Kawuma daughter", title, brief, img_url);
////        list.add(article);
////
////        article = new Consultant("Kasadha son", title, brief, img_url);
////        list.add(article);
//
//        //mAdapter.notifyDataSetChanged();
//    }
//
//
//
//}
