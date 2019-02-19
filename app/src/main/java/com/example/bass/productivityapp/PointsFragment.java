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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 4/9/2017.
 */

public class PointsFragment extends Fragment {
    private static final String TITLE = "Points";

    private static int test = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test++;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.points_fragment, container, false);
        Log.d(TITLE, "onCreateView: started.");

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView mainTextView = getView().findViewById(R.id.test_text_view);
        mainTextView.setText(Integer.toString(test));
    }


    public CharSequence getTitle(){
        return TITLE;
    }

}
