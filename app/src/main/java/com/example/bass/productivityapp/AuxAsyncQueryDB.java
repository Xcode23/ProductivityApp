package com.example.bass.productivityapp;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuxAsyncQueryDB extends AsyncTask<String, Void, ResultSet> {

    public Statement statement = null;

    public AuxAsyncQueryDB(Statement stmt){
        this.statement = stmt;
    }

    protected ResultSet doInBackground(String... query) {
        ResultSet rs = null;
        try {
            rs = this.statement.executeQuery(query[0]);
        }catch(Exception e){Log.d("Exception", e.getClass().toString());
            Log.d("Exception", e.getMessage());}

        return rs;

    }
}
