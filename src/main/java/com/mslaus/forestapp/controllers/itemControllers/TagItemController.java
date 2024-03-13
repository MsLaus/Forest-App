package com.mslaus.forestapp.controllers.itemControllers;

import com.mslaus.forestapp.SQLConnection;
import com.mslaus.forestapp.controllers.viewControllers.TagsController;
import com.mslaus.forestapp.entities.Tag;
import com.mslaus.forestapp.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class TagItemController extends SQLConnection {

    @FXML
    public VBox vbox;

    @FXML
    public HBox colour;

    @FXML
    public Label name;
    @FXML
    public Button deleteButton;

    public void setData(Tag tag){

        name.setText(tag.getName());
        colour.setStyle("-fx-background-radius: 100; -fx-background-color: " +tag.getColor());

        // TODO: fix delete button
        deleteButton.setOnAction(event -> {
            User user = new User();
            removeTag(user.getId(), tag.getName());
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Tag deleted.");
            dialog.setContentText("The tag was successfully deleted.");
            ButtonType type = new ButtonType("Ok");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        });

    }

    public String getName(){
        return name.getText();
    }
}
