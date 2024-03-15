package com.mslaus.forestapp.helpers;

import com.mslaus.forestapp.objects.ShopItem;

public class ItemHelper {

    private static ShopItem shopItem;

    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        ItemHelper.shopItem = shopItem;
    }
}
