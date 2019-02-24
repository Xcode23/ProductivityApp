package com.example.bass.productivityapp;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuxAsyncQueryDB extends AsyncTask<String, Void, Object> {

    protected Object doInBackground(String... queryInfo) {

        String database = queryInfo[0];
        String user = queryInfo[1];
        String password = queryInfo[2];
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Log.d("HELP","DBStart");
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(database, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(queryInfo[4]);
            Log.d("TEST", "Tudo OK");

            if(queryInfo[3].equals("points"))return getPointsData(rs);

        }catch(Exception e){
            Log.d("TEST", "YUP");Log.d("TEST", e.getClass().toString());
            Log.d("TEST", e.getMessage());
        }
        return null;
    }

    private ArrayList<String[]> getPointsData(ResultSet rs) throws SQLException {
        ArrayList newPoints = new ArrayList();
        while(rs.next()) {
            String[] newpoint = new String[3];
            newpoint[0] = Integer.toString(rs.getInt(2));
            newpoint[1] = rs.getDate(3).toString();
            newpoint[2] = rs.getString(4);
            newPoints.add(newpoint);
        }
        Statement stmt = rs.getStatement();
        Connection con = stmt.getConnection();
        rs.close();
        stmt.close();
        con.close();
        return newPoints;
    }
}
