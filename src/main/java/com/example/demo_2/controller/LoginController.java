package com.example.demo_2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController extends CommonController {
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

    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        changeScene(stage, "signup.fxml");
    }

    public void signInButtonOnAction(ActionEvent e) throws SQLException {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateSignIn();
        } else {
            signInMessageLabel.setText("Please enter username and password");
        }
    }

    public void validateSignIn() throws SQLException {
        String verifySignIn = "SELECT count(*) FROM useraccounts WHERE Username = ? AND Password = ? ;";
        PreparedStatement statement = connection.prepareStatement(verifySignIn);

        statement.setString(1, usernameTextField.getText());
        statement.setString(2, passwordPasswordField.getText());

        try {
            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    if(usernameTextField.getText().equals("admin")){
                        Stage stage = (Stage) signInButton.getScene().getWindow();
                        changeScene(stage,"admin.fxml");
                    } else {
                        setUsername(usernameTextField.getText());
                        Stage stage = (Stage) signInButton.getScene().getWindow();
                        changeScene(stage,"userHome.fxml");
                    }
                } else {
                    signInMessageLabel.setText("Invalid login. Please try again");
                }
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}