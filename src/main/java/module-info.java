module com.example.demo_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires lombok;

    opens com.example.demo_2 to javafx.fxml;
    exports com.example.demo_2;
    exports com.example.demo_2.controller;
    opens com.example.demo_2.controller to javafx.fxml;
}