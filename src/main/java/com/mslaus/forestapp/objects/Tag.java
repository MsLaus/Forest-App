package com.mslaus.forestapp.objects;

public class Tag {

    private int userId;

    private String name;

    private  String color;

    public Tag(int userId, String name, String color) {
        this.userId = userId;
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String colour) {
        color = colour;
    }
}
