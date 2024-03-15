package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.SQLConnection;
import com.mslaus.forestapp.objects.Task;
import com.mslaus.forestapp.objects.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewTaskController extends SQLConnection {

    @FXML
    TextField titleTextField, descriptionTextField;
    User user = new User();
    private Stage stage;
    private Scene scene;

    @FXML
    private void addTask(ActionEvent e) throws IOException {

        String title = titleTextField.getText();
        String description = descriptionTextField.getText();

        Task task = new Task(title, description, "false");

        insertTask(user.getId(),task);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/task-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
