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
    private onFoodListener listener;

    public FoodsAdaptor(ArrayList<Food> data, Context context, onFoodListener listener) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.food_row,parent,false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = data.get(position);
        holder.nameField.setText(food.getName());
        if(food.getCategories().size() != 0) {
            holder.categoryField.setText(food.getCategories().get(0).getName() + " ...");
        } else {
            holder.categoryField.setText("No category");
        }
       //loading image from food images

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameField;
        TextView categoryField;
        ImageView imageField;
        onFoodListener onFoodListener;

        public ViewHolder(@NonNull View itemView, onFoodListener onFoodListener) {
            super(itemView);
            nameField = itemView.findViewById(R.id.foodNameText);
            categoryField = itemView.findViewById(R.id.categoriesText);
            imageField = itemView.findViewById(R.id.foodImageRecycler);
            this.onFoodListener = onFoodListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onFoodListener.onClick(getAdapterPosition());
        }
    }

    public interface onFoodListener {
        void onClick(int position);
    }
}
