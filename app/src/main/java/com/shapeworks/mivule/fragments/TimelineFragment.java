package com.shapeworks.mivule.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.shapeworks.mivule.Adapters.NewsfeedAdapter;
import com.shapeworks.mivule.Entities.Articles;
import com.shapeworks.mivule.Entities.Newsfeed;
import com.shapeworks.mivule.Entities.Users;
import com.shapeworks.mivule.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.shapeworks.mivule.SettingsActivity;
import com.shapeworks.mivule.detail.DetailActivity;
import com.shapeworks.mivule.detail.DetailFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TimelineFragment extends Fragment implements
        NewsfeedAdapter.OnNewsfeedSelectedListener {

    private FirebaseDatabase fireDb = FirebaseDatabase.getInstance();
    private DatabaseReference fireRef = fireDb.getReference("news");
    private RecyclerView mRecycler;
    private NewsfeedAdapter mAdapter, sAdapter;
    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseStorage mStorage = FirebaseStorage.getInstance();

    private static final String TAG = "Projects";
    private static final int RC_SIGN_IN = 9001;
    private static final int LIMIT = 50;
    private FirebaseFirestore mFirestore;
    private Bundle data;
    private Query mQuery;

    private RecyclerView.LayoutManager mLayoutManager;
    private ViewGroup mEmptyView;
    private List<Articles> emptyArticle = new ArrayList<>();
    private List<Articles> articles = new ArrayList<>();
    private List<Users> list_02 = new ArrayList<>();


    public TimelineFragment() {
        // Required empty public constructor

    }




//    private void populateDb(){
//
//        fireRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                articles.add(dataSnapshot.getValue(Articles.class));
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Articles consult = dataSnapshot.getValue(Articles.class);
//                int index = getItemIndex(consult);
//                //list.set(index, consult);
//                articles.set(index, consult);
//                mAdapter.notifyItemChanged(index);
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Articles consult = dataSnapshot.getValue(Articles.class);
//                int index = getItemIndex(consult);
//                articles.remove(index);
//                mAdapter.notifyItemRemoved(index);
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler = rootView.findViewById(R.id.newsfeed_recycler);
        mEmptyView = rootView.findViewById(R.id.view_empty);
        setHasOptionsMenu(true);
        FirebaseFirestore.setLoggingEnabled(true);
        initFirestore();
        initRecyclerView();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
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

        }else if(id == R.id.action_inbox){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initFirestore() {
        // TODO(developer): Implement
        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("newsfeeds")
                //.whereEqualTo("email", mAuth.getEmail())
                .orderBy("recordDate", Query.Direction.DESCENDING)
                .limit(LIMIT);
    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new NewsfeedAdapter(mQuery, this) {

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home2, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            mViewModel.setIsSigningIn(false);
//
//            if (resultCode != RESULT_OK && shouldStartSignIn()) {
//                startSignIn();
//            }
//        }
//    }

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

//    private void showTodoToast() {
//        Toast.makeText(this, "TODO: Implement", Toast.LENGTH_SHORT).show();
//    }

    public static DetailFragment newInstance(DocumentSnapshot snap) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("name", snap.toObject(Newsfeed.class).getTitle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onNewsfeedSelected(DocumentSnapshot restaurant) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
//        intent.putExtra("content", (Serializable) restaurant.toObject(Newsfeed.class));
        intent.putExtra("name", restaurant.toObject(Newsfeed.class).getTitle());
        intent.putExtra("brief", restaurant.toObject(Newsfeed.class).getBrief());
        intent.putExtra("comments", restaurant.toObject(Newsfeed.class).getDownloadUrls());
        intent.putExtra("image", restaurant.toObject(Newsfeed.class).getDownloadUrls());
        startActivity(intent);
//        DetailFragment fragment = new DetailFragment();
//        Bundle args = new Bundle();
//        args.putString("name", restaurant.toObject(Newsfeed.class).getTitle());
//        fragment.setArguments(args);

//
//        return fragment;
    }

//    public void updateList(){
//        fireRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                list_02.add(dataSnapshot.getValue(Users.class));
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Users consult = dataSnapshot.getValue(Users.class);
//                int index = getItemIndex(consult);
//                //list.set(index, consult);
//                list_02.set(index, consult);
//                mAdapter.notifyItemChanged(index);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Users consult = dataSnapshot.getValue(Users.class);
//                int index = getItemIndex(consult);
//                list.remove(index);
//                mAdapter.notifyItemRemoved(index);
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    private int getItemIndex(Articles consult){
        int index = -1;
        for(int i=0; i < articles.size(); i++){
            if (articles.get(i).key.equals(consult.key)){
                index = i;
                break;
            }
        }
        return index;
    }
}
