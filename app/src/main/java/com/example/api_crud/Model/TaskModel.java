package com.example.api_crud.Model;

public class TaskModel {
    private String name;

    public TaskModel() {
    }

    public TaskModel(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    private boolean isDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
