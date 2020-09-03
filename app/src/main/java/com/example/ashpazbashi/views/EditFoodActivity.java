package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;

public class EditFoodActivity extends AppCompatActivity {

    private static Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
    }


    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        EditFoodActivity.food = food;
    }
}