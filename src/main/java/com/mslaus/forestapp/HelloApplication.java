package com.mslaus.forestapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/views/welcome.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 800);
            stage.setTitle("Forest App");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch();
    }
}
