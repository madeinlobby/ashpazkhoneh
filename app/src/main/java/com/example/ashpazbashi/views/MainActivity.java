package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.controllers.MainController;
import com.example.ashpazbashi.localDatabase.DatabaseHelper;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.views.recyclerViewAdaptors.FoodsAdaptor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FoodsAdaptor.onFoodListener {

    public static MainController controller = new MainController();
    public static DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        controller.addDefaultCategories();
        controller.readFromDB(myDb);

        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodsAdaptor foodsAdaptor = new FoodsAdaptor((ArrayList<Food>) controller.getAllFoods(),this, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(foodsAdaptor);
    }

    public void addNewFoodButton(View view) {
        Intent intent = new Intent(this,AddFoodActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(int position) {

        Food currentFood = controller.getAllFoods().get(position);
        EditFoodActivity.setFood(currentFood);
        Intent intent = new Intent(this, EditFoodActivity.class);
        startActivity(intent);

    }
}