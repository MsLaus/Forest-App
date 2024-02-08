package com.mslaus.forestapp.Beans;

public class Achievement {

    private String title;
    private String status;
    private String description;
    private int reward;
    private boolean received;

    public Achievement(String title, String status, String description, int reward, boolean received) {
        this.title = title;
        this.status = status;
        this.description = description;
        this.reward = reward;
        this.received = received;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
