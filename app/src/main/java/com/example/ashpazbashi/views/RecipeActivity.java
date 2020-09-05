package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.recipe.Step;
import com.example.ashpazbashi.views.recyclerViewAdaptors.StepAdaptor;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements StepAdaptor.OnStepListener {

    private static Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);



        RecyclerView recyclerView = findViewById(R.id.stepsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StepAdaptor stepAdaptor = new StepAdaptor((ArrayList<Step>) food.getRecipe().getSteps(),this,this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(stepAdaptor);


    }

    public void addStepButtonTapped(View view) {

        AddStepActivity.setRecipe(food.getRecipe());
        Intent intent = new Intent(this, AddStepActivity.class);
        startActivity(intent);
    }

    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        RecipeActivity.food = food;
    }

    @Override
    public void onClick(int position) {
        Step currentStep = food.getRecipe().getSteps().get(position);
        EditStepActivity.setStep(currentStep);
        Intent intent = new Intent(this, EditStepActivity.class);
        startActivity(intent);
    }
}