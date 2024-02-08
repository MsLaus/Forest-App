package com.mslaus.forestapp.Controllers.ItemControllers;

import com.mslaus.forestapp.Beans.ShopItem;
import com.mslaus.forestapp.Helpers.UserHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopItemController {

    @FXML
    private Label itemName, price;

    @FXML
    private ImageView itemImage;

    UserHelper userHelper = new UserHelper();

    public void setData(ShopItem item){
        Image image = new Image(item.getImageSrc());
        itemImage.setImage(image);
        itemName.setText(item.getNameItem());
        price.setText(String.valueOf(item.getPrice()));
    }

    @FXML
    private void buyItem(){


    }
}
