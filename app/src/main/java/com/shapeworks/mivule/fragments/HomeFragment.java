package com.shapeworks.mivule.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shapeworks.mivule.Adapters.ViewPagerAdapter;
import com.shapeworks.mivule.R;


public class HomeFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
//        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager);
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {

        //PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new ProjectsFragment(), "Projects");
        adapter.addFragment(new TimelineFragment(), "News");
        viewPager.setAdapter(adapter);
    }


}
