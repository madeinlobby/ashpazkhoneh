package com.example.ashpazbashi.views;

import androidx.annotation.Nullable;
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
    public static ArrayList<String> food_id;
    public static int limit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (limit == 0) {
            myDb = new DatabaseHelper(this);
            food_id = new ArrayList<>();
            controller.addDefaultCategories();
            controller.readFromDB(myDb, food_id);
            limit++;
        }

        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodsAdaptor foodsAdaptor = new FoodsAdaptor((ArrayList<Food>) controller.getAllFoods(),this, food_id,  this);
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
        intent.putExtra("id", String.valueOf(food_id.get(position)));
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
}