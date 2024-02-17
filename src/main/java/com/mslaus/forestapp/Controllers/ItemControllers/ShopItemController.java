package com.mslaus.forestapp.Controllers.ItemControllers;

import com.mslaus.forestapp.Beans.ShopItem;
import com.mslaus.forestapp.Helpers.UserHelper;
import com.mslaus.forestapp.SQLConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.sql.Connection;


public class ShopItemController extends SQLConnection {

    SQLConnection db = new SQLConnection();
    Connection conn = db.connection();

    @FXML
    public Label itemName, price;

    @FXML
    public ImageView itemImage;

    UserHelper userHelper = new UserHelper();

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

        // TODO: 2/17/2024 write functionality
        // TODO: 2/17/2024 disable the button if the item is unlocked

        buyItem(conn, userHelper.getUserId(), itemName.getText());



    }
}
