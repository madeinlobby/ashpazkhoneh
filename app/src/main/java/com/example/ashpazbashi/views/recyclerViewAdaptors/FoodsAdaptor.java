package com.example.ashpazbashi.views.recyclerViewAdaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;

import java.util.ArrayList;

public class FoodsAdaptor extends RecyclerView.Adapter<FoodsAdaptor.ViewHolder> {

    private ArrayList<Food> data;
    private LayoutInflater inflater;

    public FoodsAdaptor(ArrayList<Food> data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.food_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = data.get(position);
        holder.nameField.setText(food.getName());
        if(food.getCategories().size() != 0) {
            holder.categoryField.setText(food.getCategories().get(0).toString() + "and ...");
        } else {
            holder.categoryField.setText("No category");
        }
       //loading image from food images

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameField;
        TextView categoryField;
        ImageView imageField;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameField = itemView.findViewById(R.id.foodNameText);
            categoryField = itemView.findViewById(R.id.categoriesText);
            imageField = itemView.findViewById(R.id.foodImageRecycler);
        }
    }
}
