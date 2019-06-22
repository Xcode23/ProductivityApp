package com.example.bass.productivityapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.Gravity.CENTER_HORIZONTAL;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TableLayout mytable = getView().findViewById(R.id.schedulerTable);
        TableRow newrow = null;
        TextView newcolumn = null;
        MainActivity activity = (MainActivity) getActivity();
        ArrayList<String[]> schedulerData = activity.schedulerData;
        Log.d(TITLE, "onViewCreated: started.");


        for(int i = 0; i<schedulerData.size(); i++) {

            String[] values = schedulerData.get(i);
            newrow = new TableRow(activity);

            //Get Scheduler Name
            newcolumn = new TextView(activity);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(values[1]);
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            //Get LastDate
            newcolumn = new TextView(activity);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(values[6]);
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            //Get Status
            newcolumn = new TextView(activity);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(values[3]);
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            //Get Type
            newcolumn = new TextView(activity);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(values[11]);
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            Button newbutton = new Button(activity);
            newbutton.setOnClickListener(new SchedulerFragment.ExtraClickListener(values));
            //newbutton.setGravity(CENTER_HORIZONTAL);
            newbutton.setText("Extra");
            newbutton.setPadding(0, 0, 0, 0);
            newrow.addView(newbutton);

            mytable.addView(newrow);
            Log.d("SIZE", Float.toString(newcolumn.getTextSize()));
        }
    }

    public CharSequence getTitle(){
        return TITLE;
    }


    private class ExtraClickListener implements View.OnClickListener{
        private String[] scheduler = null;
        private String[] keys = {"Id", "Name", "CreationDate", "Status", "Frequency", " ",
                "LastDate", "Expiration", " ", "Points", "Loss", "Type"};

        public ExtraClickListener(String[] scheduler){
            this.scheduler = scheduler;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            // Get the layout inflater
            LayoutInflater inflater = requireActivity().getLayoutInflater();

            View layout = inflater.inflate(R.layout.scheduler_dialog, null);
            TableLayout table = layout.findViewById(R.id.schedulerDialogTable);

            for(int i = 1; i<scheduler.length; i++){
                TableRow newrow = new TableRow(getActivity());
                TextView textv = new TextView(getActivity());
                textv.setGravity(CENTER_HORIZONTAL);
                textv.setText(keys[i]);
                newrow.addView(textv);
                textv = new TextView(getActivity());
                textv.setGravity(CENTER_HORIZONTAL);
                textv.setText(scheduler[i]);
                newrow.addView(textv);
                table.addView(newrow);
            }

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(layout)
                    // Add action buttons
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            //MainActivity.this.finish();
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
