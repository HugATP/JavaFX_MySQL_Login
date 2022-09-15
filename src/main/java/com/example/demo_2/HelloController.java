package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e) throws ClassNotFoundException {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateLogin();

        } else {
            loginMessageLabel.setText("Please enter username and password");
        }

    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() throws ClassNotFoundException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM useraccounts " +
                             "WHERE Username = '" + usernameTextField.getText() +
                             "' AND Password = '" + passwordPasswordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome!");
                    // ? how to access data from sql database ?
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}