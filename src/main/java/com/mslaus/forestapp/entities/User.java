package com.mslaus.forestapp.entities;

public class User {

    static int id;

    static String userName;
    static int totalMinutes;
    static int totalTrees;
    static int gold;

    static String tag;

    public User(){}

    public User(int id, String userName, int totalMinutes, int totalTrees, int gold){
        User.id = id;
        User.userName = userName;
        User.totalMinutes = totalMinutes;
        User.totalTrees = totalTrees;
        User.gold = gold;
    }

    public String getTagName(){
        return tag;
    }

    public void setTagName(String tag){
        User.tag = tag;
    }

    public int getId() {
        return id;
    }
    public int getTotalTrees(){
        return totalTrees;
    }

    public void setTotal_trees(int totalTrees) {
        User.totalTrees = totalTrees;
    }

    public String getUserName() {
        return userName;
    }


    public int getTotalMinutes() {
        return totalMinutes;
    }

    public int getGold() {
        return gold;
    }

    public void setId(int id) {
        User.id = id;
    }

    public void setUserName(String userName) {
        User.userName = userName;
    }

    public void setTotalMinutes(int totalMinutes) {
        User.totalMinutes = totalMinutes;
    }

    public void setGold(int gold) {
        User.gold = gold;
    }
}
