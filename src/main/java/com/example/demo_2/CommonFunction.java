package com.example.demo_2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CommonFunction {
    // common use
    public static void changeScene(Stage stage, String path) throws IOException {
        FXMLLoader fxmlLoader_signup = new FXMLLoader(HelloApplication.class.getResource(path));
        Scene scene = new Scene(fxmlLoader_signup.load(), 600, 400);
        stage.setScene(scene);
        stage.show();

    }
}
