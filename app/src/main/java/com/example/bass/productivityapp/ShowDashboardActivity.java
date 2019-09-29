package com.example.bass.productivityapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ShowDashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEST", "testing add scheduler 2");
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_dashboard);

        ArrayList<AggregatePoint> dailyPoints = (ArrayList<AggregatePoint>) getIntent().getSerializableExtra("Dailies");
        Log.d("D/TEST","Help");
        LineChartView lineChartView = findViewById(R.id.chart);
        //String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
        //        "Oct", "Nov", "Dec"};


        //int[] yAxisData1 = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};
        //int[] yAxisData2 = {20, 20, 30, 40, 5, 10, 70, 63, 60, 5, 10, 15};
        List workValues = new ArrayList();
        List hobbyValues = new ArrayList();
        List improvValues = new ArrayList();
        List axisValues = new ArrayList();

        Line workLine = new Line(workValues).setColor(Color.parseColor("#0000ff"));
        Line hobbyLine = new Line(hobbyValues).setColor(Color.parseColor("#00ff00"));
        Line improvLine = new Line(improvValues).setColor(Color.parseColor("#ff0000"));

        Date date = null;
        AggregatePoint point = null;
        int dateCounter = 0;

        for(int i = 0; i < dailyPoints.size(); i++){
            point = dailyPoints.get(i);
            if(date==null || !date.equals(point.date)) {
                date = point.date;
                dateCounter++;
                axisValues.add(new AxisValue(dateCounter).setLabel(date.toString()));
            }
            switch(point.type){
                case 0:
                    workLine.add(new PointValue(dateCounter, ));
            }

        }

        for (int i = 0; i < yAxisData1.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData1[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        yAxisValues = new ArrayList();

        line = new Line(yAxisValues).setColor(Color.parseColor("#123456"));

        for (int i = 0; i < yAxisData2.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData2[i]));
        }

        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        yAxis.setName("Sales in millions");

        //Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        //viewport.top =105;
        //lineChartView.setMaximumViewport(viewport);
        //lineChartView.setCurrentViewport(viewport);
    }
}
