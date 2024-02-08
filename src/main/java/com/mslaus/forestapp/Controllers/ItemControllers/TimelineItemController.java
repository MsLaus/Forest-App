package com.mslaus.forestapp.Controllers.ItemControllers;

import com.mslaus.forestapp.Beans.TimeEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimelineItemController {

    @FXML
    private VBox vbox;

    @FXML
    private Label endTime, focusedtTime, tagName, time;
    public void setData(TimeEvent timeEvent){

        endTime.setText(timeEvent.getEndTime());
        focusedtTime.setText(String.valueOf(timeEvent.getTimeFocused()));
        tagName.setText(timeEvent.getTag());
        time.setText(timeEvent.getStartTime()+" - "+timeEvent.getEndTime());
    }
}
