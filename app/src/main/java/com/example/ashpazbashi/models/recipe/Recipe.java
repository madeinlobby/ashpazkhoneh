package com.example.ashpazbashi.models.recipe;

import com.example.ashpazbashi.models.Food;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Step> steps;
    private Food food;

    public Recipe(List<Step> steps, Food food) {
        this.steps = steps;
        this.food = food;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
