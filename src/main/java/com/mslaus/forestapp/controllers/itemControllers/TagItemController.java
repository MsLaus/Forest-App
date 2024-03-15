package com.mslaus.forestapp.controllers.itemControllers;

import com.mslaus.forestapp.SQLConnection;
import com.mslaus.forestapp.objects.Tag;
import com.mslaus.forestapp.objects.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TagItemController extends SQLConnection {

    @FXML
    public VBox vbox;

    @FXML
    public HBox colour;

    @FXML
    public Label name;
    @FXML
    public Button deleteButton;

    private Scene scene;
    private Stage stage;

    public void setData(Tag tag){

        name.setText(tag.getName());
        colour.setStyle("-fx-background-radius: 100; -fx-background-color: " +tag.getColor());


        name.setOnMouseClicked( (e) -> {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
                User user = new User();
                user.setTagName(tag.getName());
                Parent root = loader.load();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    public String getName(){
        return name.getText();
    }
}
