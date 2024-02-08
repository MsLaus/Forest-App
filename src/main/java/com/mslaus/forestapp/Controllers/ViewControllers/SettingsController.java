package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Helpers.UserHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class SettingsController extends SQLConnection implements Initializable {

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

    private static Stage stage;
    private static Scene scene;

    @FXML
    private Label name;

    @FXML
    private VBox menu;
    @FXML
    private Button button, forest, shop, timeline, tags, rewards, settings, friends;

    @FXML
    private TextField newName, newPass;

    @FXML
    private Label totalTime, totalTrees, gold, ach;

    UserHelper userHelper = new UserHelper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the gold value
        gold.setText(String.valueOf(getGold(conn, userHelper.getUserId())));

        //set the image of the menu button
        ImageView image = new ImageView("menu.png");
        button.setGraphic(image);
        button.setMaxSize(40, 40);
        button.setMinSize(40, 40);
        button.setContentDisplay(ContentDisplay.TOP);
        image.fitWidthProperty().bind(button.widthProperty());
        image.setPreserveRatio(true);

        //set the "name" Label's text with the user's name
        name.setText(getUsername(conn, userHelper.getUserId()));


        forest.setVisible(false);
        shop.setVisible(false);
        timeline.setVisible(false);
        tags.setVisible(false);
        rewards.setVisible(false);
        settings.setVisible(false);
        friends.setVisible(false);
        menu.setVisible(false);

        totalTime.setText(String.valueOf(getTotalMinutes(conn, userHelper.getUserId())));

        // TODO: set the tree number to the actual one
        totalTrees.setText("0");

        //set the achievement label to the number of total unlocked achievement
        ach.setText(String.valueOf(achievementsUnlocked(conn, userHelper.getUserId())));
    }

    @FXML
    private void changeName(){

        final String NAME = newName.getText();
        updateUsername(conn, userHelper.getUserId(), NAME);
        name.setText(getUsername(conn, userHelper.getUserId()));
    }

    @FXML
    private void changePassword(){
        final String PASS = newPass.getText();
        updatePassword(conn, userHelper.getUserId(), PASS);
    }

    @FXML
    private void deleteAccount(){
        deleteUser(conn, userHelper.getUserId());
    }

    @FXML
    private void logOut(ActionEvent e)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showMenu() {
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
    private void shop(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void timeline(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("timeline-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tags(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tags-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rewards(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rewards-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void forest(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void friends(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("friends.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
