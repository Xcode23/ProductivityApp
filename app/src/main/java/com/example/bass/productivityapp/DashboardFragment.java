package com.example.bass.productivityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {
    private static final String TITLE = "Dashboard";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dashboard_fragment, container, false);
        Log.d(TITLE, "onCreateView: started.");
        return view;
    }

    public CharSequence getTitle(){
        return TITLE;
    }
}
