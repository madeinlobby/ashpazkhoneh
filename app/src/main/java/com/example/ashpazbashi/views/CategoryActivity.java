package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.views.recyclerViewAdaptors.CategoryAdaptor;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RecyclerView recyclerView = findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdaptor categoryAdaptor = new CategoryAdaptor((ArrayList<Category>) MainActivity.controller.getAllCategories(),this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(categoryAdaptor);

    }
}