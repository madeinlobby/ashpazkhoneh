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
import com.example.ashpazbashi.models.ingredients.Ingredient;

import java.util.ArrayList;

public class IngredientAdaptor extends RecyclerView.Adapter<IngredientAdaptor.ViewHolder> {
    private ArrayList<Ingredient> data;
    private LayoutInflater inflater;
    private OnIngredientListener listener;

    public IngredientAdaptor(ArrayList<Ingredient> data, Context context, OnIngredientListener listener) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public ArrayList<Ingredient> getData() {
        return data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.ingredient_row, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = data.get(position);
        holder.ingredientName.setText(ingredient.getName());
        String string = ingredient.getAmount() + '/' + ingredient.getUnit();
        holder.ingredientAmount.setText(string);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView ingredientName;
        TextView ingredientAmount;
        ImageView deleteIngredientImage;
        OnIngredientListener onIngredientListener; // hamoon listenrer balayie :)

        public ViewHolder(View itemView, final OnIngredientListener onIngredientListener) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientAmount = itemView.findViewById(R.id.ingredientAmount);
            deleteIngredientImage = itemView.findViewById(R.id.removeIngredientImage);
            this.onIngredientListener = onIngredientListener;
//            itemView.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    // check if position is valid
                    onIngredientListener.onIngredientItemCLick(getAdapterPosition());
                }
            });


            deleteIngredientImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // check if position is valid
                    onIngredientListener.onDeleteIngredientClick(getAdapterPosition());
                }
            });

        }

//        @Override
//        public void onClick(View view) {
//            // check if position is valid
//            onIngredientListener.onIngredientItemCLick(getAdapterPosition());
//        }
    }


    public interface OnIngredientListener {
        void onIngredientItemCLick(int position);
        void onDeleteIngredientClick(int position);
    }


}
