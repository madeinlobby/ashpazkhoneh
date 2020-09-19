package com.example.ashpazbashi.models.recipe;

import java.util.ArrayList;

public class Step {
    private String subject;
    private String description;
    private String recipeName;
    private ArrayList<String> picAddress;


    public Step(String subject, String description, String recipeName) {
        this.subject = subject;
        this.picAddress = new ArrayList<>();
        this.description = description;
        this.recipeName = recipeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPicAddress() {
        return picAddress;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
