package com.mslaus.forestapp.Controllers.ViewControllers;

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
    private void signIn(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-in-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void logIn(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
