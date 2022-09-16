package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHomeController extends CommonController {
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
    public void loadButtonOnAction() throws SQLException {
        loadUserInfo();
    }

    public void loadUserInfo() throws SQLException {
        String loadUserData = "SELECT * FROM useraccounts WHERE Username = ?;";
        PreparedStatement statement = connection.prepareStatement(loadUserData);
        statement.setString(1, username);
        try {
            ResultSet queryResult = statement.executeQuery();
            queryResult.next();
            usernameLabel.setText(queryResult.getString("Username"));
            firstnameLabel.setText(queryResult.getString("Firstname"));
            lastnameLabel.setText(queryResult.getString("Lastname"));
            fullnameTextField.setText(firstnameLabel.getText() + ' ' + lastnameLabel.getText());
            // chưa có data về cái này
            genderLabel.setText(queryResult.getString("Username"));
            dobLabel.setText(queryResult.getString("Username"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
