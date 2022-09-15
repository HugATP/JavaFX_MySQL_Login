package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

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
            validateSignIn();

        } else {
            signInMessageLabel.setText("Please enter username and password");
        }
    }

    public void signUpButtonOnAction(ActionEvent e) throws ClassNotFoundException  {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateSignUp();
        } else {
            signInMessageLabel.setText("Please enter username and password");
        }
    }

    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void validateSignIn() throws ClassNotFoundException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String verifySignIn = "SELECT count(1) FROM useraccounts " +
                             "WHERE Username = '" + usernameTextField.getText() +
                             "' AND Password = '" + passwordPasswordField.getText() + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifySignIn);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    signInMessageLabel.setText("Welcome!");
                    // ? how to access data from sql database ?
                } else {
                    signInMessageLabel.setText("Invalid login. Please try again");
                }
            }
//            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validateSignUp() throws ClassNotFoundException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String sqlDML = "INSERT INTO newuseracc VALUES (?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlDML);

            statement.setString(1, usernameTextField.getText());
            statement.setString(2, passwordPasswordField.getText());

            int insertResult = statement.executeUpdate();
            if (insertResult > 0) {
                signInMessageLabel.setText("Hi, nice to meet you " + usernameTextField.getText());
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            signInMessageLabel.setText("Username has already existed. Please try again");
        }
    }
}