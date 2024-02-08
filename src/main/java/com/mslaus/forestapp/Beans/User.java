package com.mslaus.forestapp.Beans;

import java.util.List;

public class User {

    int id;

    String userName;
    String password;
    int totalMinutes;
    int gold;
    List<String> achievementsUnlocked;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public int getGold() {
        return gold;
    }

    public List<String> getAchievementsUnlocked() {
        return achievementsUnlocked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setAchievementsUnlocked(List<String> achievementsUnlocked) {
        this.achievementsUnlocked = achievementsUnlocked;
    }
}
