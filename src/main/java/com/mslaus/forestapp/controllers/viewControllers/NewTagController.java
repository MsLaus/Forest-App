package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.entities.Tag;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewTagController extends SQLConnection implements Initializable {

    @FXML
    public HBox colorButton;
    @FXML
    public TextField newTagName;
    @FXML
    public Button green, red, purple, pink, blue, yellow;
    User user = new User();

    public String colour;

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colorButton.setStyle("-fx-background-color: #f5f599; -fx-background-radius: 100;");
        colour = "#f3f564";

    }

    @FXML
    public void addTag(ActionEvent event) {
        if(newTagName.getText().isBlank() || newTagName.getText().isEmpty()){

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("You need to complete all the fields.");
            ButtonType type = new ButtonType("Ok");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();

        }else{

            try {
                insertTag(user.getId(), newTagName.getText(), colour );
                stage.close();

                // TODO: fix issue

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void pink() {
        colorButton.setStyle("-fx-background-color: #f883df; -fx-background-radius: 100");
        colour = "#f883df";
    }

    @FXML
    public void red() {
        colorButton.setStyle("-fx-background-color: #f73a3a; -fx-background-radius: 100");
        colour = "#f73a3a";
    }

    @FXML
    public void yellow() {
        colorButton.setStyle("-fx-background-color: #f3f564; -fx-background-radius: 100");
        colour = "#f3f564";
    }

    @FXML
    public void purple() {
        colorButton.setStyle("-fx-background-color: #c883f8; -fx-background-radius: 100");
        colour = "#c883f8";
    }

    @FXML
    public void blue() {
        colorButton.setStyle("-fx-background-color: #64c0f5; -fx-background-radius: 100");
        colour = "#64c0f5";
    }

    @FXML
    public void green(){
        colorButton.setStyle("-fx-background-color: #64f57a; -fx-background-radius: 100");
        colour = "#64f57a";
    }

}
