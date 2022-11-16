package com.example.hopeitworks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {


    public void start(Stage stage) throws  Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("patients_info.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}