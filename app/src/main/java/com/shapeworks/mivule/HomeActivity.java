package com.shapeworks.mivule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shapeworks.mivule.Adapters.ViewPagerAdapter;
import com.shapeworks.mivule.Controls.Firebase;
import com.shapeworks.mivule.fragments.HomeFragment;
import com.shapeworks.mivule.fragments.ProfileFragment;
import com.shapeworks.mivule.fragments.ProjectsFragment;
import com.shapeworks.mivule.fragments.TimelineFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shapeworks.mivule.subfragments.consult;
import com.shapeworks.mivule.subfragments.forums;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView username, emaill;
    private ImageView profImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mivule");
        setSupportActionBar(toolbar);
        FirebaseAuth auth = FirebaseAuth.getInstance();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        //fab.setBackgroundColor(0033);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        username = (TextView) headerView.findViewById(R.id.username_prof);
        emaill = (TextView) headerView.findViewById(R.id.email_prof);
        profImage = (ImageView) headerView.findViewById(R.id.profile_image);

        username.setText(auth.getCurrentUser().getDisplayName());
        emaill.setText(auth.getCurrentUser().getEmail());
                    Glide.with(profImage.getContext())
                    .load(auth.getCurrentUser().getPhotoUrl())
                    .into(profImage);

        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        /* initial fragment */
        //setFragment(new HomeFragment());

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
//        tabLayout.addTab(tabLayout.newTab().setText("Projects"));
//        tabLayout.addTab(tabLayout.newTab().setText("News"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        //viewPager.setAdapter(adapter);
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mAdapter = new MoviesAdapter(movieList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(mAdapter);
//


//        prepareMovieData();
    }


//    private void changeToolbarContent(Toolbar toolBar){
//        Context context = getApplicationContext();
//        if(context == getApplicationContext().)
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

    @Override
    protected void onDestroy() {
        signOut();
        super.onDestroy();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //getFragment(item.getItemId());
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        Intent intent = null;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        if (id == R.id.nav_projects) {

            intent = new Intent(this, ProjectsActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);


        } else if (id == R.id.nav_finances) {

            intent = new Intent(this, FinancesActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        }else if (id == R.id.nav_tasks){
            intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.nav_profile){
//            intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);
//            drawer.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.nav_messages) {

            intent = new Intent(this, InboxActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_location) {
            intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_workers) {

            intent = new Intent(this, WorkersActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_products) {

            intent = new Intent(this, ProductsActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

//        }else if (id == R.id.nav_settings){
//
//            intent = new Intent(this, SettingsActivity.class);
//            startActivity(intent);
//            drawer.closeDrawer(GravityCompat.START);

        }else if (id == R.id.nav_logout){
//            auth.logout(this);
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            signOut();
                        }
                    });
        }

//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
          return true;
    }

    public void signOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupViewPager(ViewPager viewPager) {

        //PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new TimelineFragment(), "News");
        adapter.addFragment(new consult(), "Consult");
        adapter.addFragment(new forums(), "Forums");

        viewPager.setAdapter(adapter);

    }

    public void setFragment(Fragment fragment){

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

    }



}
