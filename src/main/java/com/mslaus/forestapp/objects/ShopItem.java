package com.mslaus.forestapp.objects;

public class ShopItem {

    private String nameItem;
    private int price;
    private String imageSrc;

    public String getNameItem() {
        return nameItem;
    }


    public int getPrice() {
        return price;
    }


    public String getImageSrc() {
        return imageSrc;
    }


    public ShopItem(String nameItem, int price, String imageSrc) {
        this.nameItem = nameItem;
        this.price = price;
        this.imageSrc = setImageSrc(imageSrc);
    }

    public String setImageSrc(String result){

        return switch (result) {
            case "MUSHROOM" -> "src/main/resources/Images/ShopItems/mushroom.png";
            case "ROSE" -> "src/main/resources/Images/ShopItems/rose.png";
            case "SUNFLOWER" -> "src/main/resources/Images/ShopItems/sunflower.png";
            case "SHRUB" -> "src/main/resources/Images/ShopItems/shrub.png";
            case "PINE_TREE" -> "src/main/resources/Images/ShopItems/pine-tree.png";
            case "CHERRY_BLOSSOM" -> "src/main/resources/Images/ShopItems/cherry-blossom.png";
            case "LAVENDER" -> "src/main/resources/Images/ShopItems/lavender.png";
            default -> "";
        };

    }
}
