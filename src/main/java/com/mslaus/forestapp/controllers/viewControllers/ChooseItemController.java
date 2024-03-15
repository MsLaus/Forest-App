package com.mslaus.forestapp.controllers.viewControllers;

import com.mslaus.forestapp.controllers.itemControllers.ShopItemController;
import com.mslaus.forestapp.objects.ShopItem;
import com.mslaus.forestapp.objects.User;
import com.mslaus.forestapp.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseItemController extends SQLConnection implements Initializable {

    @FXML
    private GridPane gridPane;
    User user = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        int column = 0;
        int row = 1;

        try{

            List<ShopItem> list = getUnlockedShopItems(user.getId());

            for(ShopItem s: list){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemViews/shop-item-view.fxml"));
                HBox cardBox = fxmlLoader.load();
                ShopItemController controller = fxmlLoader.getController();
                controller.setData(s, true);

                if(column == 2){
                    column =0;
                    ++row;
                }

                gridPane.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(25));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
