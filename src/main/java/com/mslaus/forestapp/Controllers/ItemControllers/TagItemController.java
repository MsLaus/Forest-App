package com.mslaus.forestapp.Controllers.ItemControllers;

import com.mslaus.forestapp.Beans.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TagItemController {

    @FXML
    private VBox vbox;

    @FXML
    private HBox colour;

    @FXML
    private Label name;
    public void setData(Tag tag){

        name.setText(tag.getName());
        colour.setStyle("-fx-background-colour: "+tag.getColour());
        colour.setStyle("-fx-background-radius: 100");
    }
}