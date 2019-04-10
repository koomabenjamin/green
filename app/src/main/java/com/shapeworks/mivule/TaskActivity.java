package com.shapeworks.mivule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shapeworks.mivule.Adapters.ProjectsAdapter;
import com.shapeworks.mivule.Adapters.TaskAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.shapeworks.mivule.detail.DetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskActivity extends AppCompatActivity implements
        TaskAdapter.OnTaskSelectedListener{

    private TaskAdapter mAdapter;
    private Context context;
    private TextView displayEmail;
    private FloatingActionButton delete_btn;
    private Button mAddProject, mRemoveProject, mCreateProjectBtn;
    private static final String TAG = "Projects";
    private static final int RC_SIGN_IN = 9001;
    private static final int LIMIT = 500;
    private FirebaseFirestore mFirestore;
    private Toolbar toolbar;
    private Query mQuery;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private RecyclerView mProjectRecycler;
    private ViewGroup mEmptyView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final boolean AUTO_HIDE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.task_toolbar);

        toolbar.setTitle("Scheduled Tasks");
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mLayoutManager = new LinearLayoutManager(this);
        mProjectRecycler = findViewById(R.id.tasks_recycler);
        mEmptyView = findViewById(R.id.view_empty);
        FirebaseFirestore.setLoggingEnabled(true);
        initFirestore();
        initRecyclerView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void initFirestore() {
        // TODO(developer): Implement
        mFirestore = FirebaseFirestore.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        mQuery = mFirestore.collection("tasks")
//                .whereEqualTo("email", email)
                .limit(LIMIT);
    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new TaskAdapter(mQuery, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mProjectRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mProjectRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                Snackbar.make(findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
            }
        };
        mProjectRecycler.setLayoutManager(new LinearLayoutManager(this));
        mProjectRecycler.setAdapter(mAdapter);
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            //mViewModel.setIsSigningIn(false);

//            if (resultCode != RESULT_OK && shouldStartSignIn()) {
//                startSignIn();
//            }
        }
    }

//    private boolean shouldStartSignIn() {
//        return (!mViewModel.getIsSigningIn() && FirebaseAuth.getInstance().getCurrentUser() == null);
//    }

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

    private void showTodoToast() {
        Toast.makeText(this, "TODO: Implement", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskSelected(DocumentSnapshot restaurant) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
