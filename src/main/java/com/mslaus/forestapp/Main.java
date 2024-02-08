package com.mslaus.forestapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Forest App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
