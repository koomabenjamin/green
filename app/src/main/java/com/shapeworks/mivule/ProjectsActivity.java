package com.shapeworks.mivule;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shapeworks.mivule.Adapters.ProjectsAdapter;
import com.shapeworks.mivule.Entities.Projects;
import com.shapeworks.mivule.listeners.RecyclerItemClickListener;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ProjectsActivity extends AppCompatActivity implements
         ProjectsAdapter.OnProjectSelectedListener{


    private ProjectsAdapter mAdapter;
    private Context context;
    private TextView username, email;
    private FloatingActionButton delete_btn;
    private Button mAddProject, mRemoveProject, mCreateProjectBtn, mProjectVisit;
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

    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_projects);

        toolbar = (Toolbar)findViewById(R.id.project_start);
        toolbar.setTitle("Projects");
        toolbar.setSubtitle(mAuth.getCurrentUser().getEmail());
        setSupportActionBar(toolbar);

//        displayEmail = (TextView)findViewById(R.id.displayEmail);
//        displayEmail.setText(mAuth.getCurrentUser().getEmail());
        //delete_btn = (FloatingActionButton) findViewById(R.id.delete_btn);
        mCreateProjectBtn = (Button) findViewById(R.id.create_project);
        mProjectVisit = (Button) findViewById(R.id.project_visit);
        mProjectVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
//        mAddProject = (Button) findViewById(R.id.mAdd);
//        mRemoveProject = (Button) findViewById(R.id.mRemove);

//        displayEmail = (TextView) findViewById(R.id.disableHome);
////        Bundle i = getIntent().getExtras();
//        String email;
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                email= null;
//                displayEmail.setText(null);
//            } else {
//                email= extras.getString("email");
//                displayEmail.setText(email);
//            }
//        } else {
//            email= (String) savedInstanceState.getSerializable("email");
//            displayEmail.setText(email);
//        }

        username = (TextView)findViewById(R.id.username);
        email = (TextView)findViewById(R.id.email);
        //email.setText(mAuth.getCurrentUser().getEmail());

        mCreateProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        mProjectRecycler = findViewById(R.id.recycler_work);
        mEmptyView = findViewById(R.id.view_empty);
        FirebaseFirestore.setLoggingEnabled(true);
        initFirestore();
        initRecyclerView();
        
        mProjectRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mProjectRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(ProjectsActivity.this, HomeActivity.class);
//                        intent.putExtra("project_name", list.get(position).project_name);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        delete_btn.setVisibility(View.VISIBLE);

                    }
                })
        );
    }


    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create a Project")
                .setMessage("Please proceed to your web account")
                .setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://mivuleworker.firebaseapp.com/"));
                        startActivity(viewIntent);

                    }
                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            return true;

        }else if(id == R.id.action_refresh){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initFirestore() {
        // TODO(developer): Implement
        mFirestore = FirebaseFirestore.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        mQuery = mFirestore.collection("projects")
                .whereEqualTo("owner", email)
                .limit(LIMIT);
    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new ProjectsAdapter(mQuery, this) {

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

        mProjectRecycler.setLayoutManager(new GridLayoutManager(this, 2));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_projects, menu);
        return true;
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
    public void onProjectSelected(DocumentSnapshot restaurant) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
