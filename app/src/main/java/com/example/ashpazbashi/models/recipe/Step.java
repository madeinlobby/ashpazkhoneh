package com.example.ashpazbashi.models.recipe;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Step {
    private String subject;
    private String description;
    private Recipe recipe;
    private ArrayList<String> picAddress;


    public Step(String subject, String description, Recipe recipe) {
        this.subject = subject;
        this.picAddress = new ArrayList<>();
        this.description = description;
        this.recipe = recipe;
        this.recipe.getSteps().add(this);
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ArrayList<String> getPicAddress() {
        return picAddress;
    }
}
