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
import java.sql.*;

public class LoginController {
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

    public void signInButtonOnAction(ActionEvent e) throws SQLException {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateSignIn();

        } else {
            signInMessageLabel.setText("Please enter username and password");
        }
    }
    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader_signup = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader_signup.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void validateSignIn() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String verifySignIn = "SELECT count(*) FROM useraccounts WHERE Username = ? AND Password = ? ;";
        PreparedStatement statement = connection.prepareStatement(verifySignIn);

        statement.setString(1, usernameTextField.getText());
        statement.setString(2, passwordPasswordField.getText());

        try {
            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    String fullname = getNameFromDB(connection, usernameTextField.getText());
                    signInMessageLabel.setText("Welcome " + fullname + " !");
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



    public String getNameFromDB(Connection connection, String username) throws SQLException {
        String getName = "SELECT concat(FirstName, ' ' ,LastName) FROM useraccounts WHERE Username = ? ;";
        PreparedStatement statement = connection.prepareStatement(getName);
        statement.setString(1, usernameTextField.getText());
        ResultSet queryResult = statement.executeQuery();
        queryResult.next();
        return queryResult.getString(1);
    }
}