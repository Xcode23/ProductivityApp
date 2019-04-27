package com.example.bass.productivityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static android.os.Build.ID;

public class AddBountyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bounty);

        String[] items = new String[]{"Work", "Hobby","Improv"};

        Spinner dropdown = findViewById(R.id.newtype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,items);
        dropdown.setAdapter(adapter);

        Button finishbutton = findViewById(R.id.finishbountybutton);
        finishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button addbutton = findViewById(R.id.addbountybutton);
        addbutton.setOnClickListener(new addBountyClickListener());
    }

    public class addBountyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MainActivity act = (MainActivity) getParent();
            String database = "jdbc:mariadb://" + act.credentials.get("host") + ":" + act.credentials.get("port") + "/" + act.credentials.get("database");
            String user = act.credentials.get("user");
            String password = act.credentials.get("password");
            StringBuilder tempQuery = new StringBuilder("INSERT INTO Bounties (Points, DueDate, NegaPoints, Tag, Type) " +
                    "VALUES (");

            EditText points = findViewById(R.id.newpoints);
            tempQuery.append(points.getText());
            tempQuery.append(" ,\"");
            points.setText("");

            EditText duedate = findViewById(R.id.newdate);
            tempQuery.append(duedate.getText());
            tempQuery.append("\" ,");
            duedate.setText("");

            EditText negapoints = findViewById(R.id.newloss);
            tempQuery.append(negapoints.getText());
            tempQuery.append(" ,\"");
            negapoints.setText("");

            EditText tag = findViewById(R.id.newtag);
            tempQuery.append(tag.getText());
            tempQuery.append("\", ");
            tag.setText("");

            Spinner dropdown = findViewById(R.id.newtype);
            tempQuery.append(Utils.FromType(dropdown.getSelectedItem().toString()));
            tempQuery.append(");");

            String query = tempQuery.toString();

            Log.d("TEST", database);
            Log.d("TEST", user);
            Log.d("TEST", password);
            Log.d("TEST", query);

            try {
                new AuxAsyncUpdateDB().execute(database, user, password, query).get();
                Log.d("TEST", "ALLOK!!!");
            }catch(Exception e){
                Log.d("TEST", "YET ANOTHER SQL EXCEPTION");
            }
        }
    }

}
