package com.example.ashpazbashi.models;

import com.example.ashpazbashi.views.MainActivity;

public class Category {

    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        MainActivity.controller.getAllCategories().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
