package com.example.demo_2.controller;

import com.example.demo_2.UserAccount;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserHomeController extends CommonController implements Initializable {
    @FXML
    private Label usernameLabel;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label fullnameTextField;
    public void backButtonOnAction(ActionEvent e) throws IOException {
        super.backButtonOnAction(e, "login.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadUserInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadUserInfo() throws SQLException {
        String loadUserData = "SELECT * FROM useraccount WHERE Username = ?;";
        PreparedStatement statement = connection.prepareStatement(loadUserData);
        statement.setString(1, username);
        try {
            ResultSet queryResult = statement.executeQuery();
            queryResult.next();
            // get String from column in Database
            usernameLabel.setText(queryResult.getString("Username"));
            firstnameLabel.setText(queryResult.getString("Firstname"));
            lastnameLabel.setText(queryResult.getString("Lastname"));
            fullnameTextField.setText(firstnameLabel.getText() + ' ' + lastnameLabel.getText());
            genderLabel.setText(queryResult.getString("Gender"));
            dobLabel.setText(queryResult.getString("DOB"));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
