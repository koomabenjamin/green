package com.shapeworks.mivule.Adapters;

/**
 * Created by KoomaBenjamin on 29/10/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shapeworks.mivule.fragments.ProfileFragment;
import com.shapeworks.mivule.fragments.ProjectsFragment;
import com.shapeworks.mivule.fragments.TimelineFragment;

import java.util.ArrayList;
import java.util.List;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfileFragment profile = new ProfileFragment();
                return profile;
            case 1:
                ProjectsFragment project = new ProjectsFragment();
                return project;
            case 2:
                TimelineFragment news = new TimelineFragment();
                return news;
            default:
                return null;
        }
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
