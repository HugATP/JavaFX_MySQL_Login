package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connection = connectNow.getConnection();
    @FXML
    private Button backButton;
    @FXML
    private Button quitButton;
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



    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


    public void backButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        CommonFunction.changeScene(stage,"login.fxml");
    }


    public void registerButtonOnAction(ActionEvent e) throws ClassNotFoundException  {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateRegister();
        } else {
            signUpMessageLabel.setText("Please enter all the fields!");
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
                signUpMessageLabel.setText("Hi, nice to meet you " + firstNameTextField.getText() + ' ' + lastNameTextField.getText());
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            signUpMessageLabel.setText("Username has already existed");
        }
    }


}
