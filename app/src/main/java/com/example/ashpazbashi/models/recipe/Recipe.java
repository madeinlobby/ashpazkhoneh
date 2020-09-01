package com.example.ashpazbashi.models.recipe;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Step> steps;

    public Recipe(List<Step> steps) {
        this.steps = new ArrayList<>();
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
