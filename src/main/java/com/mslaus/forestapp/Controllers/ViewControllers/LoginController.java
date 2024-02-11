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

    public static Stage stage;
    public static Scene scene;

    @FXML
    public PasswordField password;

    @FXML
    public Label statusLabel;

    @FXML
    public TextField username;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

    @FXML
    public void logIn(ActionEvent e) throws IOException {


        final String USERNAME = username.getText();
        final String PASSWORDTEXT = password.getText();

        if(!validate(conn, USERNAME, PASSWORDTEXT)){
            statusLabel.setText("The username or the password is not correct!");
        }else {
            int id = getId(conn, USERNAME);
            UserHelper userHelper = new UserHelper();
            userHelper.setUserId(id);
            TimeLabelHelper helper = new TimeLabelHelper();
            helper.setTime(60);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }
}
