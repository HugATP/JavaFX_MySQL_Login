package com.example.demo_2.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController extends CommonController {
    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label logInMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        changeScene(stage, "signup.fxml");
    }

    public void logInButtonOnAction(ActionEvent e) throws SQLException {
        if(usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()) {
            logInMessageLabel.setText("Please enter username and password");
        } else {
            validatelogIn();
        }
    }

    public void validatelogIn() throws SQLException {
        String verifylogIn = "SELECT * FROM useraccount WHERE username = ? AND password = ? ;";
        PreparedStatement statement = connection.prepareStatement(verifylogIn);

        statement.setString(1, usernameTextField.getText());
        statement.setString(2, passwordPasswordField.getText());

        try {
            System.out.println(statement);
            ResultSet queryResult = statement.executeQuery();

            queryResult.next();
            if (queryResult.getInt("userID") > 0) {
                setUserID(queryResult.getInt("userID"));

                if(usernameTextField.getText().equals("admin")){
                    Stage stage = (Stage) logInButton.getScene().getWindow();
                    changeScene(stage,"admin.fxml");
                } else {
                    Stage stage = (Stage) logInButton.getScene().getWindow();
                    changeScene(stage,"userHome.fxml");
                }
            } else {
                System.out.println("invalid");
                logInMessageLabel.setText("Invalid login. Please try again");
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}