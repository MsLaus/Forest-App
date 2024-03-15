package com.mslaus.forestapp.enums;

public enum ShopItems {

    ROSE(150),
    LAVENDER(300),
    SHRUB(0),
    PINE_TREE(100),
    CHERRY_BLOSSOM(500),
    MUSHROOM(150),
    SUNFLOWER(300);

    final int price;

    ShopItems(int price) {

        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
