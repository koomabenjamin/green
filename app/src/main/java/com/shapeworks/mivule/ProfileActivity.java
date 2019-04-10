package com.shapeworks.mivule;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.shapeworks.mivule.Adapters.ViewPagerAdapter;
import com.shapeworks.mivule.fragments.ProfileFragment;
import com.shapeworks.mivule.fragments.ProjectsFragment;
import com.shapeworks.mivule.fragments.TimelineFragment;
import com.shapeworks.mivule.profile.GeneralFragment;
import com.shapeworks.mivule.profile.HiddenFragment;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar_new);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.profile_tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.profile_pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

   }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {

        //PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralFragment(), "Public");
        adapter.addFragment(new HiddenFragment(), "Private");
        viewPager.setAdapter(adapter);

    }

}
