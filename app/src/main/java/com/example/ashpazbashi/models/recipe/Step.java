package com.example.ashpazbashi.models.recipe;

import android.media.Image;

import java.util.List;

public class Step {
    private String description;
    //list of pictures and videos will be added later


    public Step(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
