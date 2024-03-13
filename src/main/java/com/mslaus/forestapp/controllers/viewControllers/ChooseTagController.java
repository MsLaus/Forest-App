package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.entities.Tag;
import com.mslaus.forestapp.controllers.itemControllers.TagItemController;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseTagController extends SQLConnection implements Initializable {

    @FXML
    VBox vbox;
    User user = new User();

    public List<Tag> list;

    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            //list = new ArrayList<>(listOfTags());

            for (Tag tag : list) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemViews/tag-item-view.fxml"));
                VBox vBox = fxmlLoader.load();
                TagItemController controller = fxmlLoader.getController();
                controller.setData(tag);
                vbox.getChildren().add(vBox);

                vbox.setOnMouseClicked( (e) -> {
                    // TODO: set tag name after the tag is clicked
                    stage.close();
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public void setStage(Stage stage){
        this.stage = stage;
    }
}
