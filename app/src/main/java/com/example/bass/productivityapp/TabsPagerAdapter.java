package com.example.bass.productivityapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragmentList = new Fragment[] {null, null, null, null};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void updateFragment(int i){

        Fragment newFragment = null;

        switch(i){
            case 0:
                newFragment = new PointsFragment();
                break;
            case 1:
                newFragment = new BountiesFragment();
                break;
            case 2:
                newFragment = new SchedulerFragment();
                break;
            case 3:
                newFragment = new DashboardFragment();
                break;

        }

        //if(fragmentList[i]!=null) (getSupportFragmentManager()).remove(fragmentList[i]).commitNow();
        fragmentList[i] = newFragment;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList[i];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence str = "";
        switch(position){
            case 0:
                str = "Points";
                break;
            case 1:
                str = "Bounties";
                break;
            case 2:
                str = "Scheduler";
                break;
            case 3:
                str = "Dashboard";
                break;
        }
        return str;
    }
}
