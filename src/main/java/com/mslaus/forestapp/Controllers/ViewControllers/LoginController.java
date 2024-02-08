package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Helpers.TimeLabelHelper;
import com.mslaus.forestapp.Helpers.UserHelper;
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
import java.sql.Connection;

public class LoginController extends SQLConnection {

    private static Stage stage;
    private static Scene scene;

    @FXML
    private PasswordField password;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField username;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

    @FXML
    private void logIn(ActionEvent e) throws IOException {


        final String USERNAME = username.getText();
        final String PASSWORDTEXT = password.getText();

        if(!validate(conn, USERNAME, PASSWORDTEXT)){
            statusLabel.setText("The username or the password is not correct!");
        }else {
            int id = getId(conn, USERNAME, PASSWORDTEXT);
            UserHelper userHelper = new UserHelper();
            userHelper.setUserId(id);
            TimeLabelHelper helper = new TimeLabelHelper();
            helper.setTime(60);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }
}
