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
    private Button quitButton;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label signInMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void signInButtonOnAction(ActionEvent e) throws ClassNotFoundException {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validatesignIn();

        } else {
            signInMessageLabel.setText("Please enter username and password");
        }
    }

    public void signUpButtonOnAction(ActionEvent e) {

    }

    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void validatesignIn() throws ClassNotFoundException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifysignIn = "SELECT count(1) FROM useraccounts " +
                             "WHERE Username = '" + usernameTextField.getText() +
                             "' AND Password = '" + passwordPasswordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifysignIn);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    signInMessageLabel.setText("Welcome!");
                    // ? how to access data from sql database ?
                } else {
                    signInMessageLabel.setText("Invalid login. Please try again");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}