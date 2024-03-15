package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.objects.Tag;
import com.mslaus.forestapp.controllers.itemControllers.TagItemController;
import com.mslaus.forestapp.SQLConnection;
import com.mslaus.forestapp.objects.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseTagController extends SQLConnection implements Initializable {

    @FXML
    VBox vbox;

    public List<Tag> list;
    User user = new User();
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            list = new ArrayList<>(listOfTags(user.getId()));


            for (Tag tag : list) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemViews/tag-item-view.fxml"));
                VBox vBox = fxmlLoader.load();
                TagItemController controller = fxmlLoader.getController();
                controller.setData(tag);
                vbox.getChildren().add(vBox);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addTag(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/newTag-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
