package com.example.bass.productivityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEST", "testing add scheduler 2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduler);

        String[] types = new String[]{"Work", "Hobby","Improv"};
        String[] times = new String[]{"Days", "Weeks","Months", "Years"};

        Spinner frequency_dropdown = findViewById(R.id.frequencytype);
        ArrayAdapter<String> frequency_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,times);
        frequency_dropdown.setAdapter(frequency_adapter);

        Spinner expiration_dropdown = findViewById(R.id.expirationtype);
        ArrayAdapter<String> expiration_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,times);
        expiration_dropdown.setAdapter(expiration_adapter);

        Spinner type_dropdown = findViewById(R.id.newtype);
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,types);
        type_dropdown.setAdapter(type_adapter);

        Button finishbutton = findViewById(R.id.finishschedulerbutton);
        finishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button addbutton = findViewById(R.id.addschedulerbutton);
        addbutton.setOnClickListener(new AddSchedulerActivity.addSchedulerClickListener());

        Log.d("TEST", "testing add scheduler 3");
    }

    public class addSchedulerClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MainActivity act = (MainActivity) getParent();
            String database = "jdbc:mariadb://" + act.credentials.get("host") + ":" + act.credentials.get("port")
                    + "/" + act.credentials.get("database");
            String user = act.credentials.get("user");
            String password = act.credentials.get("password");
            StringBuilder tempQuery = new StringBuilder("INSERT INTO Schedulers (Name, Creation, Status, FrequencyQuantity, FrequencyType, " +
                    "LastDate, ExpirationQuantity, ExpirationType, Points, NegaPoints, Type) " +
                    "VALUES (\"");

            EditText name = findViewById(R.id.newname);
            tempQuery.append(name.getText());
            tempQuery.append("\", \"");
            name.setText("");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date creationDate = new Date(System.currentTimeMillis());
            tempQuery.append(formatter.format(creationDate));
            tempQuery.append("\", 1, ");

            EditText frequencyValue = findViewById(R.id.newfrequency);
            tempQuery.append(frequencyValue.getText());
            tempQuery.append(", ");
            frequencyValue.setText("");

            Spinner frequencyType = findViewById(R.id.frequencytype);
            tempQuery.append(Utils.fromTimeQuantity(frequencyType.getSelectedItem().toString()));
            tempQuery.append(", \"");

            Date lastdate = new Date(0);
            tempQuery.append(formatter.format(lastdate));
            tempQuery.append("\", ");

            EditText expirationValue = findViewById(R.id.newexpiration);
            tempQuery.append(expirationValue.getText());
            tempQuery.append(", ");
            expirationValue.setText("");

            Spinner expirationType = findViewById(R.id.expirationtype);
            tempQuery.append(Utils.fromTimeQuantity(expirationType.getSelectedItem().toString()));
            tempQuery.append(", ");

            EditText points = findViewById(R.id.newpoints);
            tempQuery.append(points.getText());
            tempQuery.append(", ");
            points.setText("");

            EditText negapoints = findViewById(R.id.newnegapoints);
            tempQuery.append(negapoints.getText());
            tempQuery.append(", ");
            negapoints.setText("");

            Spinner type = findViewById(R.id.newtype);
            tempQuery.append(Utils.fromType(type.getSelectedItem().toString()));
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
