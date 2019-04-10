package com.shapeworks.mivule.detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shapeworks.mivule.R;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    private TextView content, content2, content3;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        content = findViewById(R.id.content2);
        content2 = findViewById(R.id.content3);
        content3 = findViewById(R.id.content4);
        image = findViewById(R.id.imageView3);

        Bundle extraa = getIntent().getExtras();
        content.setText(extraa.getString("name"));
        content2.setText(extraa.getString("brief"));
        content3.setText(extraa.getString("comments"));
        //image.setImageURI(Uri.parse(extraa.getString("image")));

        Glide.with(image.getContext())
                .load(extraa.getString("image"))
                .into(image);

        toolbar.setTitle(extraa.getString("name"));



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}
