package com.example.demo_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController extends CommonController {
    @FXML
    private Button searchButton;
    @FXML
    private TextField usernameSearchTextField;
    @FXML
    private TextField fullnameSearchTextField;

    public void backButtonOnAction(ActionEvent e) throws IOException {
        super.backButtonOnAction(e, "login.fxml");
    }

    public void searchButtonOnAction(ActionEvent e) throws SQLException {
        if(usernameSearchTextField.getText().isBlank() && fullnameSearchTextField.getText().isBlank()) {
            System.out.println("hello");
        } else {
            search();
        }
    }

    public void search() throws SQLException {
        String queryUser = "SELECT * FROM useraccounts WHERE Username LIKE '%"
                        + usernameSearchTextField.getText()
                        + "%' AND LastName LIKE '%"
                        + fullnameSearchTextField.getText() + "%';";
        PreparedStatement statement = connection.prepareStatement(queryUser);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println("Username: " + resultSet.getString("Username"));
            System.out.println("Password: " + resultSet.getString("Password"));
            System.out.print("Fullname: " + resultSet.getString("Firstname") + ' ');
            System.out.println(resultSet.getString("Lastname"));
            System.out.println();
        }
    }
}
