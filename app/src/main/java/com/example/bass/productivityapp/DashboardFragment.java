package com.example.bass.productivityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class DashboardFragment extends Fragment {
    private static final String TITLE = "Dashboard";
    private ArrayList<AggregatePoint> dashboardpoints;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dashboard_fragment, container, false);
        Log.d(TITLE, "onCreateView: started.");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button callbutton = getView().findViewById(R.id.calldashboard);
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowDashboardActivity.class);
                intent.putExtra("Dailies", ((MainActivity)getActivity()).dailyData);
                startActivity(intent);
            }
        });
    }

    public CharSequence getTitle(){
        return TITLE;
    }
}
