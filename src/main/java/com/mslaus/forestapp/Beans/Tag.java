package com.mslaus.forestapp.Beans;

public class Tag {

    private int userId;

    private String name;

    private String colour;

    public Tag(int userId, String name, String colour) {
        this.userId = userId;
        this.name = name;
        this.colour = colour;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
