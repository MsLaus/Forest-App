package com.mslaus.forestapp.objects;

public class Task {

    private static String title;
    private static String description;
    private static String completed;

    public Task(String title, String description, String completed) {
        Task.title = title;
        Task.description = description;
        Task.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String isCompleted() {
        return completed;
    }
}
