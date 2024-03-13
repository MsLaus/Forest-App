package com.mslaus.forestapp.controllers.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private static Stage stage;
    private static Scene scene;


    @FXML
    public void signIn(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/signIn-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logIn(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/logIn-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
