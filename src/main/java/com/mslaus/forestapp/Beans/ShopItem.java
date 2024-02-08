package com.mslaus.forestapp.Beans;

public class ShopItem {

    private String nameItem;
    private int price;
    private String imageSrc;

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public ShopItem(String nameItem, int price, String imageSrc) {
        this.nameItem = nameItem;
        this.price = price;
        this.imageSrc = imageSrc;
    }
}
