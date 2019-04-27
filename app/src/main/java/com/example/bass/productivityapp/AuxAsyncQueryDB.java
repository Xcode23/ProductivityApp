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

            if(queryInfo[3].equals("get_points"))return getPointsData(rs);
            if(queryInfo[3].equals("get_bounties"))return getBountiesData(rs);
            if(queryInfo[3].equals("get_schedulers"))return getSchedulersData(rs);
            if(queryInfo[3].equals("get_daily"))return getDailyData(rs);

        }catch(Exception e){
            Log.d("TEST", "YUP");Log.d("TEST", e.getClass().toString());
            Log.d("TEST", e.getMessage());
        }
        return null;
    }

    private ArrayList<String[]> getPointsData(ResultSet rs) throws SQLException {
        ArrayList newPoints = new ArrayList();
        while(rs.next()) {
            String[] newpoint = new String[4];
            newpoint[0] = Integer.toString(rs.getInt(2));
            newpoint[1] = rs.getDate(3).toString();
            newpoint[2] = rs.getString(4);
            newpoint[3] = Utils.ToType(rs.getInt(5));
            newPoints.add(newpoint);
        }
        Statement stmt = rs.getStatement();
        Connection con = stmt.getConnection();
        rs.close();
        stmt.close();
        con.close();
        return newPoints;
    }

    private ArrayList<String[]> getBountiesData(ResultSet rs) throws SQLException {
        ArrayList newBounties = new ArrayList();
        while(rs.next()) {
            String[] newbounty = new String[6];
            newbounty[0] = Integer.toString(rs.getInt(1));
            newbounty[1] = Integer.toString(rs.getInt(2));
            newbounty[2] = rs.getDate(3).toString();
            newbounty[3] = Integer.toString(rs.getInt(4));
            newbounty[4] = rs.getString(5);
            newbounty[5] = Utils.ToType(rs.getInt(6));
            newBounties.add(newbounty);
        }
        Statement stmt = rs.getStatement();
        Connection con = stmt.getConnection();
        rs.close();
        stmt.close();
        con.close();
        return newBounties;
    }

    private ArrayList<String[]> getSchedulersData(ResultSet rs) throws SQLException {
        ArrayList newSchedulers = new ArrayList();
        while(rs.next()) {
            String[] newscheduler = new String[12];
            newscheduler[0] = Integer.toString(rs.getInt(1));
            newscheduler[1] = rs.getString(2);
            newscheduler[2] = rs.getDate(3).toString();
            newscheduler[3] = Boolean.toString(rs.getInt(4) == 1);
            newscheduler[4] = Integer.toString(rs.getInt(5));
            newscheduler[5] = Utils.ToTimeQuantity(rs.getInt(6));
            newscheduler[6] = rs.getDate(7).toString();
            newscheduler[7] = Integer.toString(rs.getInt(8));
            newscheduler[8] = Utils.ToTimeQuantity(rs.getInt(9));
            newscheduler[9] = Integer.toString(rs.getInt(10));
            newscheduler[10] = Integer.toString(rs.getInt(11));
            newscheduler[11] = Utils.ToType(rs.getInt(12));
            newSchedulers.add(newscheduler);
        }
        Statement stmt = rs.getStatement();
        Connection con = stmt.getConnection();
        rs.close();
        stmt.close();
        con.close();
        return newSchedulers;
    }

    private ArrayList<String[]> getDailyData(ResultSet rs) throws SQLException {
        ArrayList newPoints = new ArrayList();
        while(rs.next()) {
            String[] newpoint = new String[3];
            newpoint[0] = rs.getDate(1).toString();
            newpoint[1] = Integer.toString(rs.getInt(2));
            newpoint[2] = Utils.ToType(rs.getInt(3));
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
