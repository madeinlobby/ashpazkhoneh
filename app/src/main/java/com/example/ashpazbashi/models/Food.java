package com.example.ashpazbashi.models;

import com.example.ashpazbashi.models.ingredients.Ingredient;
import com.example.ashpazbashi.models.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private List<Category> categories;
    private String description;
    private List<Ingredient> ingredients;
    private Recipe recipe;
    //list of pictures and videos will be added later

    public Food(String name, List<Category> categories, String description, List<Ingredient> ingredients, Recipe recipe) {
        this.ingredients = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.name = name;
        this.categories = categories;
        this.description = description;
        this.ingredients = ingredients;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
