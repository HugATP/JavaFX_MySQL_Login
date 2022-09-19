package com.example.demo_2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

abstract public class AdminCommonController extends CommonController {
    @FXML
    protected Button searchButton;
    @FXML
    protected Button resetButton;
    @FXML
    protected Button UACButton;
    @FXML
    protected Button GSCButton;
    @FXML
    protected TextField usernameSearchTextField;
    @FXML
    protected TextField nameSearchTextField;

    public void backButtonOnAction() throws IOException {
        super.backButtonOnAction("login.fxml");
    }
    public void UACButtonOnAction() throws IOException {
        Stage stage = (Stage) UACButton.getScene().getWindow();
        changeScene(stage, "admin_user-control.fxml");
    }
    public void GSCButtonOnAction() throws IOException {
        Stage stage = (Stage) GSCButton.getScene().getWindow();
        changeScene(stage, "admin_score-control.fxml");
    }
    public void searchButtonOnAction() throws SQLException {
        search();
    }
    abstract public void search() throws SQLException ;
    public void resetButtonOnAction() {
        usernameSearchTextField.setText("");
        nameSearchTextField.setText("");
        reset();
    }
    abstract public void reset();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * KeyPressed.
         */
        nameSearchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    search();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        usernameSearchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    search();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
