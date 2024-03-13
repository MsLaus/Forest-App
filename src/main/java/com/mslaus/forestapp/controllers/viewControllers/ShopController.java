package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.entities.ShopItem;
import com.mslaus.forestapp.controllers.itemControllers.ShopItemController;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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

public class ShopController extends SQLConnection implements Initializable {

    @FXML
    public Label gold;

    @FXML
    public Button button, forest, shop, timeline, tags, rewards, settings, friends;
    @FXML
    public VBox menu;
    @FXML
    public GridPane gridPane;
    public List<ShopItem> list;

    User user = new User();

    public Stage stage;
    public Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the gold value
        gold.setText(String.valueOf(getGold(user.getId())));

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

        int column = 0;
        int row = 1;

        try{
            list = new ArrayList<>(shopItems());

            for(ShopItem s: list){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemViews/shop-item-view.fxml"));
                HBox cardBox = fxmlLoader.load();
                ShopItemController controller = fxmlLoader.getController();
                controller.setData(s);

                if(column == 2){
                    column =0;
                    ++row;
                }

                gridPane.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(25));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<ShopItem> shopItems() throws SQLException {

        Connection conn = connection();
        list = new ArrayList<>();
        String query = String.format("SELECT * FROM shop WHERE user_id = %d AND status ='locked' ", user.getId());
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new ShopItem(
                    resultSet.getString("item"),
                    resultSet.getInt("cost"),
                    setImageSrc(resultSet.getString("item"))
            ));
        }

        return list;
    }

    public String setImageSrc(String result){

        return switch (result) {
            case "MUSHROOM" -> "src/main/resources/Images/ShopItems/mushroom.png";
            case "ROSE" -> "src/main/resources/Images/ShopItems/rose.png";
            case "SUNFLOWER" -> "src/main/resources/Images/ShopItems/sunflower.png";
            case "SHRUB" -> "src/main/resources/Images/ShopItems/shrub.png";
            case "PINE_TREE" -> "src/main/resources/Images/ShopItems/pine-tree.png";
            case "CHERRY_BLOSSOM" -> "src/main/resources/Images/ShopItems/cherry-blossom.png";
            case "LAVENDER" -> "src/main/resources/Images/ShopItems/lavender.png";
            default -> "";
        };

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
    public void forest(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
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
