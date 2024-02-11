package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Helpers.UserHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class NewTagController extends SQLConnection implements Initializable {

    @FXML
    public HBox colorButton;

    @FXML
    public TextField newTagName;
    UserHelper userHelper;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

    public String colour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void addTag() {

        insertTag(conn, userHelper.getUserId(), newTagName.getText(), colour );

    }

    @FXML
    public void pink() {
        colorButton.setStyle("-fx-background-color: #f883df;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#f883df";
    }

    @FXML
    public void red() {
        colorButton.setStyle("-fx-background-color: #f73a3a;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#f73a3a";
    }

    @FXML
    public void yellow() {
        colorButton.setStyle("-fx-background-color: #f3f564;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#f3f564";
    }

    @FXML
    public void purple() {
        colorButton.setStyle("-fx-background-color: #c883f8;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#c883f8";
    }

    @FXML
    public void blue() {
        colorButton.setStyle("-fx-background-color: #64c0f5;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#64c0f5";
    }

    @FXML
    public void green() {
        colorButton.setStyle("-fx-background-color: #64f57a;");
        colorButton.setStyle("-fx-background-radius: 100");
        colour = "#64f57a";
    }
}
