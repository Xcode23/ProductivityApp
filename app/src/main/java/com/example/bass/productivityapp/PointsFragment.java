package com.example.bass.productivityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class PointsFragment extends Fragment {
    private static final String TITLE = "Points";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        TableLayout mytable = getView().findViewById(R.id.pointsTable);
        TableRow newrow = null;
        TextView newcolumn = null;
        MainActivity activity = (MainActivity) getActivity();
        ArrayList<String[]> pointsData = activity.pointsData;
        Log.d(TITLE, "onViewCreated: started.");


        for(String[] values: pointsData) {
            newrow = new TableRow(activity);

            for(String value: values) {
                newcolumn = new TextView(activity);
                newcolumn.setGravity(CENTER_HORIZONTAL);
                newcolumn.setText(value);
                newcolumn.setPadding(3, 3, 3, 3);
                newrow.addView(newcolumn);
            }

            mytable.addView(newrow);
        }
    }


    public CharSequence getTitle(){
        return TITLE;
    }

}
