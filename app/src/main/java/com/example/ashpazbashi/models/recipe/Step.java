package com.example.ashpazbashi.models.recipe;

import android.media.Image;

import java.util.List;

public class Step {
    private String subject;
    private String description;
    //list of pictures and videos will be added later


    public Step(String subject, String description) {
        this.subject = subject;
        this.description = description;
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
}
