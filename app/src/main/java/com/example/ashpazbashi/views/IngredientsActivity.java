package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.ingredients.Ingredient;
import com.example.ashpazbashi.views.recyclerViewAdaptors.IngredientAdaptor;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity implements IngredientAdaptor.OnIngredientListener {

    static IngredientAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        RecyclerView recyclerView = findViewById(R.id.ingredientsRecyclerView);
        IngredientAdaptor adapter = new IngredientAdaptor(new ArrayList<Ingredient>(), this, this);
        IngredientsActivity.adaptor = adapter;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addIngredientClick(View view) {
        //show dialog and get data

    }

    @Override
    public void onIngredientItemCLick(int position) {
        // when user clicks on an item
    }

    @Override
    public void onDeleteIngredientClick(int position) {
        // when user clicks on delete icon on item
    }
}