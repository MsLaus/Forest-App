package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.objects.User;
import com.mslaus.forestapp.helpers.TimeHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends SQLConnection {

    public static Stage stage;
    public static Scene scene;

    @FXML
    public PasswordField password;

    @FXML
    public Label statusLabel;

    @FXML
    public TextField username;

    TimeHelper timeHelper = new TimeHelper();


    @FXML
    public void logIn(ActionEvent e) throws IOException {


        final String USERNAME = username.getText();
        final String PASSWORDTEXT = password.getText();

        if(!validate( USERNAME, PASSWORDTEXT)){
            statusLabel.setText("The username or the password is not correct!");
        }else {
            User user = new User();
            int id = getId(USERNAME);
            user.setId(id);
            user.setUserName(USERNAME);
            user.setTotalMinutes(getTotalMinutes(id));
            user.setGold(getGold(id));
            user.setTotalTrees(getTotalTrees(id));
            timeHelper.setTime(120);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
