package com.example.demo_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    public Connection databaseLink;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;
    public Connection getConnection() throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            databaseUser = prop.getProperty("username");
            databasePassword = prop.getProperty("password");
            databaseName =  prop.getProperty("databaseName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
