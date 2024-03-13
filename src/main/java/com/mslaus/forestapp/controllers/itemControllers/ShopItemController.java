package com.mslaus.forestapp.controllers.itemControllers;

import com.mslaus.forestapp.entities.ShopItem;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class ShopItemController extends SQLConnection {

    @FXML
    public Label itemName, price;

    @FXML
    public ImageView itemImage;

    User user = new User();

    public void setData(ShopItem item){

        try {

            FileInputStream input = new FileInputStream(item.getImageSrc());

            Image image = new Image(input);
            itemImage.setImage(image);

            itemName.setText(item.getNameItem());
            price.setText(String.valueOf(item.getPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void buyItem(){

        buyItem(user.getId(), itemName.getText());
    }
}
