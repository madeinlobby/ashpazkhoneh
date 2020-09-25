package com.example.ashpazbashi.controllers;

import android.database.Cursor;
import com.example.ashpazbashi.localDatabase.DatabaseHelper;
import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.ingredients.Ingredient;
import com.example.ashpazbashi.models.recipe.Recipe;
import com.example.ashpazbashi.models.recipe.Step;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void insertFoodInDB(DatabaseHelper databaseHelper, Food food) {
        Gson gson = new Gson();
        String categoriesJSON = gson.toJson(food.getCategories());
        String ingredientsJSON = gson.toJson(food.getIngredients());
        String recipeJSON = gson.toJson(food.getRecipe());
        String picsJSON = gson.toJson(food.getPicsAddress());

        databaseHelper.insertData(
             food.getName(), categoriesJSON, food.getDescription(),
             ingredientsJSON, recipeJSON, picsJSON
        );
    }

    public void readFromDB(DatabaseHelper databaseHelper, ArrayList<String> id) {
        Cursor res = databaseHelper.getAllData();
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            if (!id.contains(res.getString(0))) {
                Food food = new Food(res.getString(1));
                id.add(res.getString(0));
                ArrayList<Category> destCat = new ArrayList<>();
                ArrayList<Ingredient> destIngredient = new ArrayList<>();
                ArrayList<String> pics = new ArrayList<>();

                Collections.addAll(destCat, new Gson().fromJson(res.getString(2), Category[].class));
                Collections.addAll(destIngredient, new Gson().fromJson(res.getString(4), Ingredient[].class));
                Collections.addAll(pics, new Gson().fromJson(res.getString(6), String[].class));
                
                food.setCategories(destCat);
                food.setDescription(res.getString(3));
                food.setIngredients(destIngredient);
                food.setRecipe(new Gson().fromJson(res.getString(5), Recipe.class));
                food.setPicsAddress(pics);
                allFoods.add(food);
            }
        }
    }

    public void updateRowDB(DatabaseHelper databaseHelper, Food food, String id) {
        Gson gson = new Gson();
        String categoriesJSON = gson.toJson(food.getCategories());
        String ingredientsJSON = gson.toJson(food.getIngredients());
        String recipeJSON = gson.toJson(food.getRecipe());
        String picsJSON = gson.toJson(food.getPicsAddress());

        databaseHelper.updateData(
                id, food.getName(), categoriesJSON, food.getDescription(),
                ingredientsJSON, recipeJSON, picsJSON);
    }

    public void deleteRowDB(DatabaseHelper databaseHelper, String id) {
        databaseHelper.deleteData(id);
    }

    public void addPicIndex(Food food, String path, int index) {
        food.getPicsAddress().add(index, path);
    }

    public void addFood(Food food) {
        allFoods.add(food);
    }

    public void addStepToRecipe(Step step, Recipe recipe) {
        recipe.getSteps().add(step);
    }

    public void addStepPicIndex(Step step, String path, int index) {
        step.getPicAddress().add(index, path);
    }

    public void removeStep(Step step) {
        for (Food food : allFoods) {
            if (food.getRecipe().getFoodName().equals(step.getRecipeName())) {
                food.getRecipe().getSteps().remove(step);
            }
        }
    }

    public void removeFood(Food food) {
        allFoods.remove(food);
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
        new Category("pasta and noodle",null);
        new Category("fast food",null);
        new Category("soup",null);
        new Category("bread and cookie",null);
        new Category("ice cream",null);
        new Category("appetizer",null);
        new Category("main food",null);
        new Category("dessert",null);
        new Category("traditional",null);
        new Category("rice",null);
        new Category("etc",null);
    }
}
