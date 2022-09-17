package com.example.demo_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppMain extends Application {
    public static FXMLLoader fxmlLoader_userhome = new FXMLLoader(AppMain.class.getResource("login.fxml"));

    //    public static Stage stage = new Stage();
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(fxmlLoader_userhome.load(), 600, 400);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
//        Image icon_ = new Image("java/img/icons8-login-64.png");
//        stage.getIcons().add(icon_);
        stage.setTitle("Login app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}