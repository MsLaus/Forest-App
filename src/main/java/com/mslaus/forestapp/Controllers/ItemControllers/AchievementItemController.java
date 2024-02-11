package com.mslaus.forestapp.Controllers.ItemControllers;

import com.mslaus.forestapp.Beans.Achievement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AchievementItemController {

    @FXML
    public Label title, description, status;
    public void setData(Achievement ach){

        title.setText(ach.getTitle());
        description.setText(ach.getDescription());
        status.setText(ach.getStatus());
    }
}
