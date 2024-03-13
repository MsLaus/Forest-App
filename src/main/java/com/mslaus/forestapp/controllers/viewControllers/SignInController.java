package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

public class SignInController extends SQLConnection  {

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public PasswordField password1;

    @FXML
    private Label statusLabel;

    public Scene scene;
    public Stage stage;

    @FXML
    public void signIn(ActionEvent e) throws IOException {

        String pass = password.getText();
        String pass2 = password1.getText();

        //Check if the password is longer or equal to 8 characters.
        if (pass.length() < 8) {
           statusLabel.setText("The password must have at least 8 characters.");
        }

        //Checks if any field is empty
        if (pass.isEmpty() || pass2.isEmpty() || username.getText().isEmpty()) {
           statusLabel.setText("You need to complete all the fields.");
        }

        //Checks if the username is unique
        if(uniqueUsername(username.getText())){

            //Checks if the passwords fields' values are the same
            if (pass.equals(pass2)) {

                //encrypt password, and store it in the db
                final String PASSWORD = BCrypt.hashpw(pass, BCrypt.gensalt());
                final String USERNAME = username.getText();
                final int ID = generateID();

                //inserting a new user into the database
                insertUser(ID, USERNAME, PASSWORD);

                //Getting the user to work with data
                User user = getUser(ID);
                user.setTagName("study");
                //Changing the interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
                Parent root = loader.load();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {

                statusLabel.setText("You need to write the same password.");
            }

        }else{
            statusLabel.setText("This username is already used.");

        }


    }

    private int generateID(){

        int id = 0;
        int generatedId;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        generatedId = Integer.parseInt(String.valueOf(Long.valueOf(sb.toString()).longValue()));

        if(checkID(generatedId)){
            generateID();
        }else id = generatedId;

        return id;

    }
}
