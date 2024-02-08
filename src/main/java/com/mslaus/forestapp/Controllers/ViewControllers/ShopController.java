package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Beans.ShopItem;
import com.mslaus.forestapp.Controllers.ItemControllers.ShopItemController;
import com.mslaus.forestapp.Helpers.UserHelper;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Label gold;

    @FXML
    private Button button, forest, shop, timeline, tags, rewards, settings, friends;
    @FXML
    private VBox menu;
    @FXML
    private GridPane gridPane;
    private List<ShopItem> list;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();
    UserHelper userHelper = new UserHelper();

    private Stage stage;
    private Scene scene;

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

            // TODO: 2/7/2024 fix overlapping issue

            for(ShopItem s: list){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("shop-item-view.fxml"));
                HBox cardBox = fxmlLoader.load();
                ShopItemController controller = fxmlLoader.getController();
                controller.setData(s);

                if(column == 3){
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

    private List<ShopItem> shopItems() throws SQLException {

        list = new ArrayList<>();
        String query = String.format("SELECT * from shop where id = %d and status = '%s'",userHelper.getUserId(), "locked");
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new ShopItem(
                    resultSet.getString("item"),
                    resultSet.getInt("price"),
                    setImageSrc(resultSet.getString("item"))
            ));
        }

        return list;
    }

    private String setImageSrc(String result){

        // TODO: 2/7/2024 insert into database "shop" these items for every user

        return switch (result) {
            case "mushroom" -> "D:\\Proiecte\\Java\\Java Projects\\Basic Projects\\ForestApp Demo\\src\\main\\resources\\mushroom.png";
            case "tulip" -> "D:\\Proiecte\\Java\\Java Projects\\Basic Projects\\ForestApp Demo\\src\\main\\resources\\tulip.png";
            case "rose" -> "D:\\Proiecte\\Java\\Java Projects\\Basic Projects\\ForestApp Demo\\src\\main\\resources\\rose.png";
            case "sunflower" -> "D:\\Proiecte\\Java\\Java Projects\\Basic Projects\\ForestApp Demo\\src\\main\\resources\\sunflower.png";
            default -> "";
        };
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
    private void forest(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
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
    private void settings(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
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
