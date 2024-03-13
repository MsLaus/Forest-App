package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.entities.Achievement;
import com.mslaus.forestapp.controllers.itemControllers.AchievementItemController;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.enums.Achievements;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AchievementController extends SQLConnection implements Initializable {

    @FXML
    public Button forest, shop, timeline, tags, rewards, settings, friends, button;

    @FXML
    public Label gold;
    public Stage stage;
    public Scene scene;

    @FXML
    public VBox menu;

    @FXML
    public VBox vbox;

    public List<Achievement> list;
    User user = new User();
    private static int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id = user.getId();

        checkAchievements(id);

        //set the gold value
        gold.setText(String.valueOf(getGold(id)));

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


        //shows all the achievements
        try{

            list = new ArrayList<>(reward());

            for(Achievement e : list){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemViews/achievement-item-view.fxml"));
                HBox cardBox = fxmlLoader.load();
                AchievementItemController controller = fxmlLoader.getController();
                controller.setData(e);
                vbox.getChildren().add(cardBox);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //checks for completed achievements and if the rewards are received
        for( Achievements achievements : Achievements.values()){
            if(checkStatusAchievement(id, String.valueOf(achievements))){
                giveReward(id, String.valueOf(achievements));
            }
        }


    }

    public List<Achievement> reward() throws SQLException {

        Connection conn = connection();

        list = new ArrayList<>();
        String query = "select * from achievements where user_id = " + id;
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            list.add(new Achievement(
                    resultSet.getString("title"),
                    resultSet.getString("status"),
                    resultSet.getString("description"),
                    resultSet.getInt("rewards"),
                    resultSet.getBoolean("received")

            ));
        }

        return list;
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
    public void tags(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/tags-view.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/settings-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void friends(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/friends-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
