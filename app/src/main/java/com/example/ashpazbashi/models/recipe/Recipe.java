package com.example.ashpazbashi.models.recipe;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Step> steps;
    private String foodName;

    public Recipe(String foodNme) {
        this.steps = new ArrayList<>();
        this.foodName = foodNme;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
