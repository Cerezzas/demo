package com.example.taskapi.model;

public class TaskRequest {

    private String title;

    public TaskRequest() {
    }

    public TaskRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}