package com.shapeworks.mivule.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.shapeworks.mivule.R;

public class d_project extends AppCompatActivity {

    private TextView project_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.project_toolbar);
        toolbar.setTitle("Project");
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        project_name = (TextView) findViewById(R.id.textView2);

        project_name.setText(getIntent().getExtras().getString("project_name"));


    }

    @Override
    public boolean onSupportNavigateUp() {
        //return super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }
}
