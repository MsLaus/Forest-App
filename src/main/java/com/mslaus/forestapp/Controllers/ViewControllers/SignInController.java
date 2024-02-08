package com.mslaus.forestapp.Controllers.ViewControllers;

import com.mslaus.forestapp.Helpers.UserHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

public class SignInController extends SQLConnection  {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();


    private Scene scene;
    private Stage stage;


    @FXML
    private void signIn(ActionEvent e) throws IOException {

        final String PASSWORD = password.getText();
        final String REPEATEDPASSWORD = password1.getText();

        //Check if the password is longer or equal to 8 characters.
        if (PASSWORD.length() < 8) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("The password must have 8 letters or digits.");
            ButtonType type = new ButtonType("Ok");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        }

        //Checks if any field is empty
        if (PASSWORD.isEmpty() || REPEATEDPASSWORD.isEmpty() || username.getText().isEmpty()) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("You need to complete all the fields.");
            ButtonType type = new ButtonType("Ok");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        }

        //Checks if the passwords fields' value are the same
        if (PASSWORD.equals(REPEATEDPASSWORD)) {

            final String USERNAME = username.getText();
            final int ID = generateID();

            //inserting a new user into the database
            insertUser(conn, ID, USERNAME, PASSWORD, 100, 0);


            //Setting the fields of the userHelper
            UserHelper userHelper = new UserHelper();
            userHelper.setUserId(ID);

            //Changing the interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml"));
            Parent root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {

            //A dialog Box appears if the passwords do not match
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            dialog.setContentText("You need to write the same password.");
            ButtonType type = new ButtonType("Ok");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        }

    }

    private int generateID(){

        int id = 0;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        if(checkID(conn, Integer.parseInt(String.valueOf(Long.valueOf(sb.toString()).longValue())))){
            generateID();
        }else id = Integer.parseInt(String.valueOf(Long.valueOf(sb.toString()).longValue()));

        return id;

    }
}
