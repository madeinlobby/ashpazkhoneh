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

    public Food findFoodByName(String name) {
        for (Food food : allFoods) {
            if (food.getName().equals(name)) {
                return food;
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

    public void addDefaultCategories() {
        Category pasta = new Category("pasta and noodle",null);
        Category fastFood = new Category("fast food",null);
        Category soup = new Category("soup",null);
        Category bread = new Category("bread and cookie",null);
        Category iceCream = new Category("ice cream",null);
        Category appetizer = new Category("appetizer",null);
        Category mainFood = new Category("main food",null);
        Category dessert = new Category("dessert",null);
        Category traditional = new Category("traditional",null);
        Category rice = new Category("rice",null);
        Category etc = new Category("etc",null);
    }
}
