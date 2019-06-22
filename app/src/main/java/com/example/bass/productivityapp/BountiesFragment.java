package com.example.bass.productivityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_DATETIME_VARIATION_DATE;
import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;
import static android.view.Gravity.CENTER_HORIZONTAL;

public class BountiesFragment extends Fragment {
    private static final String TITLE = "Bounties";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.bounties_fragment, container, false);
        Log.d(TITLE, "onCreateView: started.");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TableLayout mytable = getView().findViewById(R.id.bountiesTable);
        TableRow newrow = null;
        TextView newcolumn = null;
        MainActivity activity = (MainActivity) getActivity();
        ArrayList<String[]> bountiesData = activity.bountyData;
        Log.d(TITLE, "onViewCreated: started.");


        for(final String[] values: bountiesData) {
            newrow = new TableRow(activity);

            for(int i=1; i<values.length; i++) {
                newcolumn = new TextView(activity);
                newcolumn.setGravity(CENTER_HORIZONTAL);
                newcolumn.setText(values[i]);
                newcolumn.setPadding(3, 3, 3, 3);
                newrow.addView(newcolumn);
            }

            Button newbutton = new Button(activity);
            newbutton.setOnClickListener(new ClaimClickListener(values[0]));
            //newbutton.setGravity(CENTER_HORIZONTAL);
            newbutton.setText("Claim");
            newbutton.setPadding(0, 0, 0, 0);
            newrow.addView(newbutton);

            mytable.addView(newrow);
            Log.d("SIZE", Float.toString(newcolumn.getTextSize()));
        }
    }

    public CharSequence getTitle(){
        return TITLE;
    }


    private class ClaimClickListener implements View.OnClickListener{
        private String ID = null;

        public ClaimClickListener(String ID){
            this.ID = ID;
        }

        @Override
        public void onClick(View v) {
            MainActivity act = (MainActivity) getActivity();
            String database = "jdbc:mariadb://" + act.credentials.get("host") + ":" + act.credentials.get("port") + "/" + act.credentials.get("database");
            String user = act.credentials.get("user");
            String password = act.credentials.get("password");
            String query1 = "INSERT INTO Points (Points, Date, Tag, Type) " +
                    "SELECT Points, curdate(), Tag, Type from Bounties " +
                    "WHERE ID = " + ID + ";";
            String query2 = "DELETE from Bounties where ID = " + ID + ";";
            Log.d("TEST", database);
            Log.d("TEST", user);
            Log.d("TEST", password);
            Log.d("TEST", ID);
            Log.d("TEST", query1);
            Log.d("TEST", query2);

            try {
                new AuxAsyncUpdateDB().execute(database, user, password, query1).get();
                new AuxAsyncUpdateDB().execute(database, user, password, query2).get();
                Log.d("TEST", "ALLOK!!!");
                act.updateData(1);
                act.updateData(0);
                act.myViewPager.getAdapter().notifyDataSetChanged();
            }catch(Exception e){
                Log.d("TEST", "YET ANOTHER EXCEPTION");
            }
        }
    }
}
