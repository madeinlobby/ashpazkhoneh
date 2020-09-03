package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;

public class AddFoodActivity extends AppCompatActivity {

    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        this.food = new Food(null);
    }

    public void recipeButtonTaped(View view) {
        EditText nameText = findViewById(R.id.editTextTextFoodName);
        EditText descriptionText = findViewById(R.id.editTextFoodDescription);
        if(!nameText.getText().toString().equals("")) {
            food.setName(nameText.getText().toString());
        }
        if (!descriptionText.getText().toString().equals("")) {
            food.setDescription(descriptionText.getText().toString());
        }
        //another if is needed for category and another for medias
        RecipeActivity.setFood(food);
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }


    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}