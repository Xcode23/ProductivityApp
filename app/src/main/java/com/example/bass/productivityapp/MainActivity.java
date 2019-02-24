package com.example.bass.productivityapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class MainActivity extends AppCompatActivity {


    public static final String CREDENTIALS = "com.example.productivityapp.CREDENTIALS";


    private TabsPagerAdapter myAdapter;
    private ViewPager myViewPager;

    public ArrayList<String[]> pointsData;
    public ArrayList<String[]> bountyData;
    public ArrayList<String[]> schedulerData;
    public HashMap<String,int[]> dashboardData;

    public static HashMap<String,String> credentials= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("HELP","so far so good");

        performFileSearch();
        //remaining "main" logic in onActivityResult because of asynchronous bullshit
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.renew_button:
                updateData(myViewPager.getCurrentItem());
                myViewPager.getAdapter().notifyDataSetChanged();
                return true;

            case R.id.add_button:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                alertDialogBuilder
                        .setMessage("The Add functionality is not available in this tab!")
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                //MainActivity.this.finish();
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private static final int READ_REQUEST_CODE = 42;
    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("*/*");
        Log.d("HELP","ok wot?");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        Log.d("HELP","ok wot?");
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            processResultData(resultData);

            //get data
            Log.d("HELP", "not yet");
            Log.d("CREDS",credentials.get("user"));
            Log.d("CREDS",credentials.get("database"));
            updateData(0);

            Log.d("MYDATA", pointsData.get(0)[0]);
            Log.d("MYDATA", pointsData.get(0)[1]);
            Log.d("MYDATA", pointsData.get(0)[2]);

            myAdapter = new TabsPagerAdapter(getSupportFragmentManager());

            myViewPager = findViewById(R.id.pager);
            myViewPager.setAdapter(myAdapter);
        }
    }

    private void processResultData(Intent resultData){
        Uri uri = null;


        if (resultData != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(resultData.getData());
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream));
                credentials = new HashMap();
                String line;
                while ((line = reader.readLine()) != null) {
                    line=line.trim();
                    String[] parts=line.split(":");
                    for(int i=0; i<parts.length; i++){
                        parts[i]=parts[i].trim();
                    }
                    credentials.put(parts[0],parts[1]);
                }
                inputStream.close();
                reader.close();
            } catch (IOException e) {
                Log.d("Exception", e.toString());
            }
        }
    }

    private void updateData(int i) {
        String database = "jdbc:mariadb://" + credentials.get("host") + ":" + credentials.get("port") + "/" + credentials.get("database");
        String user = credentials.get("user");
        String password = credentials.get("password");
        Log.d("HELP","ok wot?");
        try{
            switch(i) {
                case 0:
                    Log.d("HELP","dunno");
                    pointsData = (ArrayList<String[]>) new AuxAsyncQueryDB().execute(database,user,password, "points",
                            "SELECT * FROM Points ORDER BY Date DESC").get();
                    break;
                case 1:
                    //rs = new AuxAsyncQueryDB().execute(database,user,password,"select * from Points").get();
                    //bountyData = getBountyData(rs);
                    break;
                case 2:
                    //rs = new AuxAsyncQueryDB().execute(database,user,password,"select * from Points").get();
                    //schedulerData = getSchedulerData(rs);
                    break;
                case 3:
                    //rs = new AuxAsyncQueryDB().execute(database,user,password,"select * from Points").get();
                    //dashboardData = getDashboardData(rs);
                    break;
            }
        }catch(Exception e){Log.d("Exception", e.toString());}
    }

    public void buildDB(){
        /*



        rs = new AuxAsyncQueryDB(stmt).execute("select * from Points").get();
        if(rs!=null)Log.d("TEST", "ALLOK");
        while(rs.next()) {
            newrow = new TableRow(this);
            newcolumn = new TextView(this);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText());
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            newcolumn = new TextView(this);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(rs.getDate(3).toString());
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            newcolumn = new TextView(this);
            newcolumn.setGravity(CENTER_HORIZONTAL);
            newcolumn.setText(rs.getString(4));
            newcolumn.setPadding(3, 3, 3, 3);
            newrow.addView(newcolumn);

            mytable.addView(newrow);
        }


        newrow = new TableRow(this);
        newcolumn = new TextView(this);
        newcolumn.setGravity(CENTER_HORIZONTAL);
        newcolumn.setText("5");
        newcolumn.setPadding(3,3,3,3);
        newrow.addView(newcolumn);
        newcolumn = new TextView(this);
        newcolumn.setGravity(CENTER_HORIZONTAL);
        newcolumn.setText("13/12/2019");
        newcolumn.setPadding(3,3,3,3);
        newrow.addView(newcolumn);
        newcolumn = new TextView(this);
        newcolumn.setGravity(CENTER_HORIZONTAL);
        newcolumn.setText("Test");
        newcolumn.setPadding(3,3,3,3);
        newrow.addView(newcolumn);

        mytable.addView(newrow);

        //Intent intent = new Intent(this, RandomKanjiActivity.class);
        //intent.putExtra(CREDENTIALS, credentials);
        //startActivity(intent);
        */
    }

}
