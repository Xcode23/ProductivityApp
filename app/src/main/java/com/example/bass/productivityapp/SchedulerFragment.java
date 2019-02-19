package com.example.bass.productivityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by User on 4/9/2017.
 */

public class SchedulerFragment extends Fragment {
    private static final String TITLE = "Scheduler";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.scheduler_fragment, container, false);
        Log.d(TITLE, "onCreateView: started.");

        return view;
    }

    public CharSequence getTitle(){
        return TITLE;
    }
}
