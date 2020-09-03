package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.recipe.Step;
import com.example.ashpazbashi.views.recyclerViewAdaptors.StepAdaptor;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private static Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        RecyclerView recyclerView = findViewById(R.id.stepsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StepAdaptor stepAdaptor = new StepAdaptor((ArrayList<Step>) food.getRecipe().getSteps(),this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(stepAdaptor);
    }

    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        RecipeActivity.food = food;
    }
}