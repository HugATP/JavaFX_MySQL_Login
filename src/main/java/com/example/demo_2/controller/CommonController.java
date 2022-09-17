package com.example.demo_2.controller;

import com.example.demo_2.DatabaseConnection;
import com.example.demo_2.AppMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CommonController implements Initializable {
    protected DatabaseConnection connectNow = new DatabaseConnection();
    protected Connection connection = connectNow.getConnection();

    // data
    protected static String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @FXML
    protected Button backButton;
    @FXML
    protected Button quitButton;

    public static void changeScene(Stage stage, String path) throws IOException {
        FXMLLoader fxmlLoader_signup = new FXMLLoader(AppMain.class.getResource(path));
        Scene scene = new Scene(fxmlLoader_signup.load(), 600, 400);
        stage.setScene(scene);
        stage.show();

    }
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    public void backButtonOnAction(ActionEvent e, String path) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        changeScene(stage, path);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
