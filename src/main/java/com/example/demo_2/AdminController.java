package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connection = connectNow.getConnection();

    @FXML
    private Button backButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField usernameSearchTextField;
    @FXML
    private TextField fullnameSearchTextField;



    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


    public void backButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader_login = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader_login.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void searchButtonOnAction(ActionEvent e) throws SQLException {
        if(usernameSearchTextField.getText().isBlank() && fullnameSearchTextField.getText().isBlank()) {
            System.out.println("hello");
        } else {
            search();
        }
    }

    public void search() throws SQLException {
        String queryUser = "SELECT * FROM useraccounts WHERE Username LIKE '%"
                        + usernameSearchTextField.getText()
                        + "%' AND LastName LIKE '%"
                        + fullnameSearchTextField.getText() + "%';";
        PreparedStatement statement = connection.prepareStatement(queryUser);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println("Username: " + resultSet.getString("Username"));
            System.out.println("Password: " + resultSet.getString("Password"));
            System.out.print("Fullname: " + resultSet.getString("Firstname") + ' ');
            System.out.println(resultSet.getString("Lastname"));
            System.out.println();
        }
    }
}
