package com.example.demo_2;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    private String databaseName;

    public Connection getConnection() {

        databaseName = "GameDatabase.db";
        String url = "jdbc:sqlite:" + databaseName;

        try {
            databaseLink = DriverManager.getConnection(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

}

