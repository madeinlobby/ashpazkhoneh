package com.example.ashpazbashi.controllers;

import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Food> allFoods;
    private List<Ingredient> allIngredients;
    private List<Category> allCategories;

    public MainController() {
        this.allFoods = new ArrayList<>();
        this.allIngredients = new ArrayList<>();
        this.allCategories = new ArrayList<>();
    }

    public Category findCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public List<Food> getAllFoods() {
        return allFoods;
    }

    public void setAllFoods(List<Food> allFoods) {
        this.allFoods = allFoods;
    }

    public List<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(List<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }
}
