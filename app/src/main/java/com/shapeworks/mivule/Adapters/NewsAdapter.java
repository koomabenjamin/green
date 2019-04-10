package com.shapeworks.mivule.Adapters;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shapeworks.mivule.Entities.Articles;
import com.shapeworks.mivule.Entities.Consultant;
import com.shapeworks.mivule.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by KoomaBenjamin on 30/10/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

    private List<Articles> articlesList;
    private Context context;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView status;
        private ImageView icon, status_image;
        private Button comment, share;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            status = (TextView) view.findViewById(R.id.news_status);
            icon = (ImageView) view.findViewById(R.id.news_icon);
            status_image = (ImageView) view.findViewById(R.id.news_image);

        }

    }


    public NewsAdapter(List<Articles> moviesList) {

        this.articlesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card_item_00, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Articles article = articlesList.get(position);
        //        holder.icon.setImageResource(article.iconImg_uri);
//        holder.status_image.setImageResource(article.statusImg_uri);
//        holder.status_image.setImageURI(Uri.parse(article.news_image));


        //holder.status_image.setImageURI(Uri.parse(article.st_img));
        //holder.status_image.setImageURI(article.st_img);
//        Glide.with(context)
//                .using(new FirebaseImageLoader())
//                .load()
//                .placeholder(R.drawable.cover)
//                .into(holder.status_image);
        //holder.status_image.setImageURI(Uri.parse(article.st_img));
        //holder.status_image.setImageURI(Uri.parse(article.news_image));

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference("content");
        //StorageReference icon = storageRef.child("/images/kyakachwa/03.png");

//        Glide.with(context)
//                    .load(article.st_img)
//                    .placeholder(R.drawable.cover)
//                    .into(holder.status_image);

        String User = user.getUid().toString();
        String author = article.author;

        if (user.getUid().toString().equals(article.author)) {

            Glide.with(context)
                    .load(article.st_img)
                    .placeholder(R.drawable.cover)
                    .into(holder.status_image);
        }
        holder.status.setText(article.status);


        //        holder.status.setText(article.status);
//        holder.icon.setImageURI(icon.getDownloadUrl().getResult());
//        holder.status_image.setImageURI(statusImage.getDownloadUrl().getResult());


    }

    @Override
    public int getItemCount() {
        if(articlesList == null){
        return 0;
        }else {
            return articlesList.size();
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

}

