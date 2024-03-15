package com.mslaus.forestapp.objects;

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

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

}
