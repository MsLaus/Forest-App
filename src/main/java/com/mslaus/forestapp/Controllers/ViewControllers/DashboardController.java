package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.HelloApplication;
import com.mslaus.forestapp.Helpers.TimeHelper;
import com.mslaus.forestapp.Helpers.TimeLabelHelper;
import com.mslaus.forestapp.Helpers.UserHelper;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardController extends SQLConnection implements Initializable {

    @FXML
    public VBox menu;

    public static Stage stage;
    public static Scene scene;

    @FXML
    public Slider slider;

    TimeLabelHelper helper = new TimeLabelHelper();

    @FXML
    public Label timeLabel = new Label(String.valueOf(helper.getTime()));
    @FXML
    public Label gold;

    @FXML
    public Button button, forest, shop, timeline, tags, rewards, settings, friends, plantButton, changeTagButton;

    int seconds = 0;

    int hours;
    int minutes;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();
    UserHelper userHelper = new UserHelper();
    TimeHelper timeHelper = new TimeHelper();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the gold value
        gold.setText(String.valueOf(getGold(conn, userHelper.getUserId())));


        //initialize the slider
        slider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldVal, Number newVal) -> {
            Integer value = Integer.valueOf(String.format("%.0f", newVal));
            timeLabel.setText(String.valueOf(value));

        });

        //set the image of the menu button
        InputStream in = getClass().getResourceAsStream("/Images/menu.png");
        Image image = new Image(in);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        button.setMaxSize(40, 40);
        button.setMinSize(40, 40);
        button.setContentDisplay(ContentDisplay.TOP);
        imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.setPreserveRatio(true);


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
        changeTagButton.setDisable(true);

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
                    updateTotalMinutes(conn, userHelper.getUserId(), focusedTime);

                    Calendar now = Calendar.getInstance();
                    //When the time is up, the current time is stored in the variable "endTime"
                    timeHelper.setEndTime(now.get(Calendar.HOUR_OF_DAY)+ ":"+ now.get(Calendar.MINUTE));

                    //the timeEvents is inserted in db
                    insertTimeEvent(conn, userHelper.getUserId(), focusedTime, timeHelper.getStartingTime(), timeHelper.getEndTime());
                    plantButton.setDisable(false);
                    button.setDisable(false);
                    changeTagButton.setDisable(false);
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

    @FXML
    public void changeTag(){
        // TODO: pop new stage, set the static field of tag to the chosen tag
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/chooseTag-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Forest App");
        stage.setScene(scene);
        stage.show();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shop-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void timeline(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/timeline-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void tags(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tags-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void rewards(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/achievement-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void settings(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void friends(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/friends-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
