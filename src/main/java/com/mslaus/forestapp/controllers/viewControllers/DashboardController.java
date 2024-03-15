package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.helpers.ItemHelper;
import com.mslaus.forestapp.objects.ShopItem;
import com.mslaus.forestapp.objects.User;
import com.mslaus.forestapp.helpers.TimeHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardController extends SQLConnection implements Initializable {

    @FXML
    public VBox menu;

    public static Stage stage;
    public static Scene scene;
    TimeHelper timeHelper = new TimeHelper();

    @FXML
    public Slider slider;

    @FXML
    public Label timeLabel = new Label(String.valueOf(timeHelper.getTime()));
    @FXML
    public Label gold;

    @FXML
    public Button button, forest, shop, timeline, tags, rewards, settings, friends, plantButton, tagName, itemButton;


    int seconds = 0;

    int hours;
    int minutes;
    User user = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the gold value
        gold.setText(String.valueOf(user.getGold()));


        //initialize the slider
        slider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldVal, Number newVal) -> {
            Integer value = Integer.valueOf(String.format("%.0f", newVal));
            timeLabel.setText(String.valueOf(value));

        });

        //initialize the tag
        if(user.getTagName() == null){
            tagName.setText("study");
        }else {
            tagName.setText(user.getTagName());
        }

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


        //set the image of the center vbox
        ItemHelper helper = new ItemHelper();
        if(helper.getShopItem() != null){

            ShopItem shopItem = helper.getShopItem();
            try {
                FileInputStream inputStream = new FileInputStream(shopItem.getImageSrc());
                Image im = new Image(inputStream);
                ImageView imageView1 = new ImageView(im);
                imageView1.setFitWidth(170);
                imageView1.setFitHeight(170);
                itemButton.setGraphic(imageView1);
                itemButton.setContentDisplay(ContentDisplay.CENTER);
                imageView1.fitWidthProperty().bind(itemButton.widthProperty());
                imageView1.setPreserveRatio(true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                FileInputStream inputStream = new FileInputStream("src/main/resources/images/shopItems/shrub.png");
                Image im = new Image(inputStream);
                ImageView imageView1 = new ImageView(im);
                imageView1.setFitWidth(170);
                imageView1.setFitHeight(170);
                itemButton.setGraphic(imageView1);
                itemButton.setContentDisplay(ContentDisplay.CENTER);
                imageView1.fitWidthProperty().bind(itemButton.widthProperty());
                imageView1.setPreserveRatio(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //starting the countdown timer
        plantButton.setOnAction(event -> {
            // Parse user input for hours, minutes, and seconds
            int time = Integer.parseInt(timeLabel.getText());
            hours = Integer.parseInt(timeLabel.getText());
            minutes = time - hours*60;

            Calendar now = Calendar.getInstance();
            timeHelper.setStartingTime(now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE));

            // Start the countdown timer
            startCountdown();
        });

        //hide the menu buttons
        forest.setVisible(false);
        shop.setVisible(false);
        timeline.setVisible(false);
        tags.setVisible(false);
        rewards.setVisible(false);
        settings.setVisible(false);
        friends.setVisible(false);
        menu.setVisible(false);

    }

    @FXML
    public void startCountdown() {

        //While the timer goes down, these 3 button are not available, after the time is up they are available
        plantButton.setDisable(true);
        button.setDisable(true);


        //store the value set with the slider
        int focusedTime = Integer.parseInt(timeLabel.getText());

        LocalTime end = LocalTime.now()
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                //make the seconds pass
                Duration remaining = Duration.between(LocalTime.now(), end);
                if (remaining.isPositive()) {
                    timeLabel.setText(format(remaining));
                } else {
                    timeLabel.setText(format(Duration.ZERO));
                    stop();
                    updateTotalMinutes(user.getId(), focusedTime);

                    Calendar now = Calendar.getInstance();
                    //When the time is up, the current time is stored in the variable "endTime"
                    timeHelper.setEndTime(now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE));

                    //the timeEvents is inserted in db
                    ItemHelper itemHelper = new ItemHelper();
                    ShopItem shopItem = itemHelper.getShopItem();
                    insertTimeEvent(user.getId(), focusedTime, timeHelper.getStartingTime(), timeHelper.getEndTime(), tagName.getText(), shopItem.getNameItem());
                    int currentTrees = user.getTotalTrees();
                    int newTrees = ++currentTrees;
                    updateTotalTrees(user.getId(), newTrees);

                    //for every 3 minutes focusing, the user gets 1 gold
                    int goldReceived = focusedTime/3;
                    updateGold(user.getId(), goldReceived);
                    user.setGold(getGold(user.getId()));
                    gold.setText(String.valueOf(user.getGold()));
                    plantButton.setDisable(false);
                    button.setDisable(false);
                }
            }

            public String format(Duration remaining) {
                return String.format("%02d:%02d:%02d",
                        remaining.toHoursPart(),
                        remaining.toMinutesPart(),
                        remaining.toSecondsPart()
                );
            }

        };
        timer.start();
    }

    public void setItem(Image image){

        ImageView imageView1 = new ImageView(image);
        itemButton.setGraphic(imageView1);
        imageView1.setFitWidth(170);
        imageView1.setFitHeight(170);
        itemButton.setGraphic(imageView1);
        itemButton.setContentDisplay(ContentDisplay.CENTER);
        imageView1.fitWidthProperty().bind(itemButton.widthProperty());
        imageView1.setPreserveRatio(true);
    }

    @FXML
    public void changeTag(ActionEvent e){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/chooseTag-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void chooseItem(ActionEvent e) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/chooseItem-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex){
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
    public void toDoList(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/task-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
