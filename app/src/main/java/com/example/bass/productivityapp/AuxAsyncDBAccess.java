package com.example.bass.productivityapp;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class AuxAsyncDBAccess extends AsyncTask<String, Void, Statement> {

    protected Statement doInBackground(String[] hostInfo) {
        String database = hostInfo[0];
        String user = hostInfo[1];
        String password = hostInfo[2];
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(database, user, password);
            stmt = con.createStatement();
            Log.d("TEST", "Tudo OK");
        }catch(Exception e){Log.d("TEST", "YUP");Log.d("TEST", e.getClass().toString());
            Log.d("TEST", e.getMessage());}

        if(stmt!=null)Log.d("TEST", "OLAOLAOLAOALOALAOL" + con.toString());
        else Log.d("TEST", "MEGAPROBLEMAS");

        return stmt;

    }

}
