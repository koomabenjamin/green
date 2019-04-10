package com.shapeworks.mivule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shapeworks.mivule.Adapters.NewsAdapter;
import com.shapeworks.mivule.Adapters.UserAdapter;
import com.shapeworks.mivule.Controls.Database;
import com.shapeworks.mivule.Entities.Articles;
import com.shapeworks.mivule.Entities.Consultant;
import com.shapeworks.mivule.Entities.Users;
import com.shapeworks.mivule.ui.dialogs.*;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DummyActivity extends AppCompatActivity {

    private Database db;
    private List<Articles> list = new ArrayList<>();
//    private List<Users> list;
    private RecyclerView recycle;
    private NewsAdapter mAdapter;
    private FirebaseDatabase fireDb;
    private DatabaseReference fireRef;

    Button view;

//    private void prepareConsultantData() {
//        Consultant Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//        Consultant = new Consultant("Kooma Benjamin", "Agricultural Expert", "2001");
//        list.add(Consultant);
//
//
//
//        mAdapter.notifyDataSetChanged();
//    }

    private void prepareArticleData() {

        int img_url = R.drawable.ic_movie;
//        int statusImg_url = R.drawable.ic_movie;
//        Articles article = new Articles("Kooma Benjamin", statusImg_url, img_url);
//        list.add(article);
//
//        article = new Articles("Kooma Benjamin", statusImg_url, img_url);
//        list.add(article);
//
//        article = new Articles("Kooma Benjamin", statusImg_url, img_url);
//        list.add(article);

        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                break;
            case 1:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        fireDb = FirebaseDatabase.getInstance();
        fireRef = fireDb.getReference("users");

//        writeNewConsult("Kooma", "King","Agricultural Specialist");
        prepareArticleData();

        recycle = (RecyclerView) findViewById(R.id.recycle);
        mAdapter = new NewsAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(mAdapter);

//        updateList();
//        prepareConsultantData();
        view = (Button) findViewById(R.id.view);
//        recycle = (RecyclerView) findViewById(R.id.recycle);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("messages");
//
//
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                list = new ArrayList<FireModel>();
//                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
//
//                    FireModel value = dataSnapshot1.getValue(FireModel.class);
//                    FireModel fire = new FireModel();
//                    String name = value.getName();
//                    String address = value.getAddress();
//                    String email = value.getEmail();
//                    fire.setName(name);
//                    fire.setEmail(email);
//                    fire.setAddress(address);
//                    list.add(fire);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Hello", "Failed to read value.", error.toException());
//            }
//        });
//
//
        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                dialog_item_00 dialog = new dialog_item_00();
//                dialog.show(getSupportFragmentManager(), "dialog");
//            }});
        @Override
        public void onClick(View v){
            dialog_item_00 dialog = new dialog_item_00();
            dialog.show(getSupportFragmentManager(), "dialog");
        }});


//
//
//                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,DummyActivity.this);
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(DummyActivity.this,2);
//                /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
//                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//                recycle.setLayoutManager(recyce);
//                recycle.setItemAnimator( new DefaultItemAnimator());
//                recycle.setAdapter(recyclerAdapter);
//
//
//
//
//            }
//        });


    }

//    public void updateList(){
//        fireRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                list.add(dataSnapshot.getValue(Users.class));
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Users consult = dataSnapshot.getValue(Users.class);
//                int index = getItemIndex(consult);
//                //list.set(index, consult);
//                list.set(index, consult);
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

//    private void writeNewConsult(String firstName, String lastName, String job) {
//        Consultant consult = new Consultant(firstName, lastName, job);
//
//        fireRef.child("consult").setValue(consult);
//    }

    private int getItemIndex(Users consult){
        int index = -1;
        for(int i=0; i < list.size(); i++){
            if (list.get(i).key.equals(consult.key)){
                index = i;
                break;
            }
        }
        return index;
    }
}


//   public class DummyActivity extends AppCompatActivity {
//
//    private DatabaseReference mDatabase;
//    private RecyclerView mRecycler;
//    List<Users> list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dummy);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mDatabase = FirebaseDatabase.getInstance().getReference("users");
//        // Creating new user node, which returns the unique key value
//        // new user node would be /users/$userid/
//        String userId = mDatabase.push().getKey();
//
//        // creating user object
//        Users user = new Users("Ravi Tamada", "ravi@androidhive.info", "peeeeeeee");
//
//        // pushing user to 'users' node using the userId
//        mDatabase.child(userId).setValue(user);
//        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Users usx = dataSnapshot.getValue(Users.class);
//
//                //Log.d(TAG, "User name: " + usx.getName() + ", email " + usx.getEmail());
//                Log.d("TAG", "Username:" + usx.name + ", email" + usx.email);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException());
//            }
//        });
//
//        ProjectAdapter recyclerAdapter = new ProjectAdapter(list, DummyActivity.this);
//        mRecycler.setLayoutManager(new LinearLayoutManager(this));
//        //  RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
//        //  recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        //  mRecycler.setLayoutManager(recyce);
//
//        mRecycler.setItemAnimator( new DefaultItemAnimator());
//        mRecycler.setAdapter(recyclerAdapter);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//}
