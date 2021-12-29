package com.baimamboukar.java.rms.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.baimamboukar.java.rms.services.interfaces.SQLiteConnectInterface;

public class SQLiteConnect implements SQLiteConnectInterface {

    @Override
    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "rms.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void close() {

    }

    @Override
    public List<String> execute() {

        return null;
    }

    public void main(String[] args) {
        SQLiteConnect sqlite = new SQLiteConnect();
        sqlite.connect();
    }

}
