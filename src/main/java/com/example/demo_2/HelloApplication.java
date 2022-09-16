package com.example.demo_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public static FXMLLoader fxmlLoader_userhome = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

    //    public static Stage stage = new Stage();
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(fxmlLoader_userhome.load(), 600, 400);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}