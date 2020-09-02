package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.controllers.MainController;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.views.recyclerViewAdaptors.FoodsAdaptor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static MainController controller = new MainController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.foodRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodsAdaptor foodsAdaptor = new FoodsAdaptor((ArrayList<Food>) controller.getAllFoods(),this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(foodsAdaptor);
    }


}