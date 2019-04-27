package com.example.bass.productivityapp;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuxAsyncUpdateDB extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... queryInfo) {
        String database = queryInfo[0];
        String user = queryInfo[1];
        String password = queryInfo[2];
        Connection con = null;
        Statement stmt = null;
        Boolean result = false;
        try {
            Log.d("HELP","DBStart");
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(database, user, password);
            stmt = con.createStatement();
            result = stmt.execute(queryInfo[3]);
            Log.d("TEST", "Tudo OK" + result.toString());

        }catch(Exception e){
            Log.d("TEST", "YUP");Log.d("TEST", e.getClass().toString());
            Log.d("TEST", e.getMessage());
        }finally{
            try{
                stmt.close();
                con.close();
            }catch(Exception e){
                Log.d("TEST", "Error closing connection!");Log.d("TEST", e.getClass().toString());
            }
        }
        return false;
    }
}
