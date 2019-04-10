package com.shapeworks.mivule.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.shapeworks.mivule.R;
import com.shapeworks.mivule.subfragments.consult;
import com.shapeworks.mivule.subfragments.forums;


public class ProjectsFragment extends Fragment {



    public ProjectsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);
//        final FoldingCell fc = (FoldingCell) rootView.findViewById(R.id.folding_cell);​
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                rootView.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
//                            case R.id.action_item1:
//                                //selectedFragment = menuItem1.newInstance("me", "men");
//                                work tasks = new work();
//                                selectedFragment = tasks;
//                                break;

                            case R.id.action_item2:
                                //selectedFragment = menuItem2.newInstance("me", "men");
                                consult consult = new consult();
                                selectedFragment = consult;
                                break;
                            case R.id.action_item3:
                                //selectedFragment = menuItem3.newInstance("me", "men");
                                forums forum = new forums();
                                selectedFragment = forum;
                                break;
                        }

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        consult work = new consult();
        transaction.replace(R.id.frame_layout, work);
        transaction.commit();
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        return rootView;
    }



}
