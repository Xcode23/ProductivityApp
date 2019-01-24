package com.example.bass.productivityapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.sql.Statement;
import java.util.HashMap;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class MainActivity extends AppCompatActivity {


    public static final String CREDENTIALS = "com.example.productivityapp.CREDENTIALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performFileSearch();
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

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            HashMap<String,String> credentials= null;
            TableLayout mytable = (TableLayout) findViewById(R.id.pointsTable);
            TableRow newrow;
            TextView newcolumn;

            if (resultData != null) {
                try {
                    credentials = readTextFromUri(resultData.getData());
                }catch(IOException e){Log.d("Exception", e.toString());}


                String database = "jdbc:mariadb://" + credentials.get("host") + ":" + credentials.get("port") + "/" + credentials.get("database");
                String user = credentials.get("user");
                String password = credentials.get("password");
                Statement stmt = null;
                ResultSet rs = null;

                String[] info = {database, user, password};
                try{
                    stmt = new AuxAsyncDBAccess().execute(info).get();
                    rs = new AuxAsyncQueryDB(stmt).execute("select * from Points").get();
                    if(rs!=null)Log.d("TEST", "ALLOK");
                    while(rs.next()){
                        newrow = new TableRow(this);
                        newcolumn = new TextView(this);
                        newcolumn.setGravity(CENTER_HORIZONTAL);
                        newcolumn.setText(Integer.toString(rs.getInt(2)));
                        newcolumn.setPadding(3,3,3,3);
                        newrow.addView(newcolumn);

                        newcolumn = new TextView(this);
                        newcolumn.setGravity(CENTER_HORIZONTAL);
                        newcolumn.setText(rs.getDate(3).toString());
                        newcolumn.setPadding(3,3,3,3);
                        newrow.addView(newcolumn);

                        newcolumn = new TextView(this);
                        newcolumn.setGravity(CENTER_HORIZONTAL);
                        newcolumn.setText(rs.getString(4));
                        newcolumn.setPadding(3,3,3,3);
                        newrow.addView(newcolumn);

                        mytable.addView(newrow);
                    }
                }catch(Exception e){Log.d("Exception", e.toString());}


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

            }
        }
    }

    private HashMap readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        HashMap credentials = new HashMap();
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
        return credentials;
    }
}
