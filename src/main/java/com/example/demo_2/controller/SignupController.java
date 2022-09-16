package com.example.demo_2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController extends CommonController {
    @FXML
    private Button registerButton;
    @FXML
    private Label signUpMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;


    public void backButtonOnAction(ActionEvent e) throws IOException {
        super.backButtonOnAction(e, "login.fxml");
    }


    public void registerButtonOnAction(ActionEvent e) throws IOException, InterruptedException {
        if(usernameTextField.getText().isBlank()
            || passwordPasswordField.getText().isBlank()
            || lastNameTextField.getText().isBlank()
            || firstNameTextField.getText().isBlank()) {
            signUpMessageLabel.setText("Please enter all the fields!");
        } else {
            validateRegister();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            setUsername(usernameTextField.getText());
            changeScene(stage, "userHome.fxml");

        }
    }

    public void validateRegister() {

        String sqlDML = "INSERT INTO useraccounts(FirstName, LastName, Username, Password) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlDML);
            statement.setString(1, firstNameTextField.getText());
            statement.setString(2, lastNameTextField.getText());
            statement.setString(3, usernameTextField.getText());
            statement.setString(4, passwordPasswordField.getText());

            int insertResult = statement.executeUpdate();
            if (insertResult > 0) {
                signUpMessageLabel.setText("Hi, nice to meet you " +
                        firstNameTextField.getText() + ' ' +
                        lastNameTextField.getText() + " <3");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            signUpMessageLabel.setText("Username has already existed!");
        }
    }


}
