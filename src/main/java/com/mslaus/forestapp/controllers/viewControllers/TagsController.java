package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.objects.Tag;
import com.mslaus.forestapp.objects.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TagsController extends SQLConnection implements Initializable {

    @FXML
    public Button forest, shop, timeline, tags, rewards, settings, friends, button;
    @FXML
    public VBox menu, vbox;
    @FXML
    public Label gold;

    @FXML
    public ListView<Tag> listView;


    public Stage stage;
    public Scene scene;

    User user = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the gold value
        gold.setText(String.valueOf(user.getGold()));

        //set the image of the menu button
        InputStream in = getClass().getResourceAsStream("/images/menu.png");
        Image image = new Image(in);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        button.setMaxSize(40, 40);
        button.setMinSize(40, 40);
        button.setContentDisplay(ContentDisplay.TOP);
        imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.setPreserveRatio(true);

        //hide the menu buttons
        forest.setVisible(false);
        shop.setVisible(false);
        timeline.setVisible(false);
        tags.setVisible(false);
        rewards.setVisible(false);
        settings.setVisible(false);
        friends.setVisible(false);
        menu.setVisible(false);


        try {
            List<Tag> list = listOfTags(user.getId());
            for (Tag tag : list) {
                listView.getItems().add(tag);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }


        listView.setCellFactory(param -> new ListCell<>() {
            private final Button deleteButton = new Button();
            private Button colorBox = new Button();

            @Override
            protected void updateItem(Tag tag, boolean empty) {
                super.updateItem(tag, empty);

                if (empty || tag == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: #deaef4;");
                } else {
                    setText(tag.getName());
                    setGraphic(deleteButton);
                    //setGraphic(colorBox);
                    setStyle("-fx-background-color: #deaef4; -fx-padding: 20px; -fx-border-width: 1px; -fx-border-color:  #e5bcf7; -fx-font-family: System Italic; -fx-font-size: 19; -fx-text-fill: #f5f599;");

                    colorBox.setStyle("-fx-background-color: " +tag.getColor()+ "; -fx-background-radius: 30;");
                    colorBox.setMaxSize(20, 20);
                    colorBox.setMinSize(20,20);
                    InputStream in1 = getClass().getResourceAsStream("/images/bin.png");
                    Image image1 = new Image(in1);
                    ImageView imageView1 = new ImageView(image1);
                    imageView1.setFitHeight(40);
                    imageView1.setFitWidth(40);
                    deleteButton.setGraphic(imageView1);
                    deleteButton.setStyle("-fx-background-color: #deaef4;");

                    deleteButton.setOnAction(actionEvent -> {
                        listView.getItems().remove(tag);

                        removeTag(user.getId(), tag.getName());
                    });
                }
            }
        });
    }

    @FXML
    public void addTag(ActionEvent e) {

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
    @FXML
    public void showMenu() {

        if (forest.isVisible()) {
            menu.setVisible(false);
            forest.setVisible(false);
            shop.setVisible(false);
            timeline.setVisible(false);
            tags.setVisible(false);
            rewards.setVisible(false);
            settings.setVisible(false);
            friends.setVisible(false);

        } else {
            menu.setVisible(true);
            forest.setVisible(true);
            shop.setVisible(true);
            timeline.setVisible(true);
            tags.setVisible(true);
            rewards.setVisible(true);
            settings.setVisible(true);
            friends.setVisible(true);

        }
    }
    @FXML
    public void shop(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/shop-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void timeline(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/timeline-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void forest(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void rewards(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/achievement-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void settings(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/settings.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toDoList(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/task-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
