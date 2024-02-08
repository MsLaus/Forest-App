package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Beans.Tag;
import com.mslaus.forestapp.Controllers.ItemControllers.TagItemController;
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
import javafx.scene.image.ImageView;
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

public class TagsController extends SQLConnection implements Initializable {

    @FXML
    private Button forest, shop, timeline, tags, rewards, settings, friends, button;
    @FXML
    private VBox menu, vbox;
    @FXML
    private Label gold;


    private Stage stage;
    private Scene scene;
    private List<Tag> list;

    UserHelper userHelper = new UserHelper();

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

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

        try{
            list = new ArrayList<>(listOfTags());

            for(Tag tag : list){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("tag-item-view.fxml"));
                VBox cardBox = fxmlLoader.load();
                TagItemController controller = fxmlLoader.getController();
                controller.setData(tag);
                vbox.getChildren().add(cardBox);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private List<Tag> listOfTags()throws SQLException {

        list = new ArrayList<>();
        String query = "select * from tags where id = " + userHelper.getUserId();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new Tag(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("colour")
            ));
        }

        return list;
    }

    @FXML
    private void addTag(ActionEvent e)throws IOException {

        // TODO: only pop up

        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-tag-view.fxml"));
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
    private void forest(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
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
