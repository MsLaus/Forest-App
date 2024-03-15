package com.mslaus.forestapp.controllers.itemControllers;

import com.mslaus.forestapp.controllers.viewControllers.DashboardController;
import com.mslaus.forestapp.objects.ShopItem;
import com.mslaus.forestapp.objects.User;
import com.mslaus.forestapp.SQLConnection;
import com.mslaus.forestapp.helpers.ItemHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class ShopItemController extends SQLConnection {

    @FXML
    HBox hbox;
    @FXML
    Button button;

    @FXML
    public Label itemName, price;

    @FXML
    public ImageView itemImage;

    User user = new User();
    ItemHelper helper = new ItemHelper();
    private Scene scene;
    private Stage stage;

    FileInputStream input;

    public void setData(ShopItem item, boolean isBought){

        try {

            input = new FileInputStream(item.getImageSrc());
            System.out.println(item.getImageSrc());

            Image image = new Image(input);
            itemImage.setImage(image);

            itemName.setText(item.getNameItem());

            if(isBought){
                hbox.setVisible(false);
                button.setText("Set");

            }else {
                price.setText(String.valueOf(item.getPrice()));
                button.setOnAction( event -> {
                    if(isBuyable(user.getId(), item.getNameItem())) {
                        buyItem(user.getId(), itemName.getText());
                    }else {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("You can't buy this item.");
                        dialog.setContentText("You do not have enough gold to buy this item.");
                        ButtonType type = new ButtonType("Ok");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.show();

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setItem(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/dashboard-view.fxml"));
        Parent root = loader.load();
        DashboardController controller = loader.getController();
        Image image = new Image(input);
        controller.setItem(image);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
