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
    private List<String> picsAddress;

    public Food(String name) {
        this.name = name;
        this.picsAddress = new ArrayList<>();
        this.ingredients = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.recipe = new Recipe(name);
    }

    public boolean hasCategory(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return true;
            }
        }
        return false;
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

    public List<String> getPicsAddress() {
        return picsAddress;
    }

    public void setPicsAddress(List<String> picsAddress) {
        this.picsAddress = picsAddress;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
