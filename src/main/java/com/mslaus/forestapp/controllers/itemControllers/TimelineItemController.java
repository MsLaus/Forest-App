package com.mslaus.forestapp.controllers.itemControllers;

import com.mslaus.forestapp.entities.TimeEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimelineItemController {

    @FXML
    public VBox vbox;

    @FXML
    public Label endTime, focusedtTime, tagName, time;
    public void setData(TimeEvent timeEvent){

        endTime.setText(timeEvent.getEndTime());
        focusedtTime.setText(String.valueOf(timeEvent.getTimeFocused()));
        tagName.setText(timeEvent.getTag());
        time.setText(timeEvent.getStartTime()+" - "+timeEvent.getEndTime());
    }
}
