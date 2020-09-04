package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.models.Food;

import java.util.ArrayList;
import java.util.Arrays;

public class AddFoodActivity extends AppCompatActivity {

    private Food food;
    String[] categoryItems;
    boolean[] checkedItems;
    Button addCategory;
    TextView tvCategory;
    ArrayList<Integer> userSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        addCategory = findViewById(R.id.addFoodCategoryButton);
        tvCategory = findViewById(R.id.foodCategoryTv);

        categoryItems = getResources().getStringArray(R.array.food_category_items);
        checkedItems = new boolean[categoryItems.length];

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddFoodActivity.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(categoryItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            userSelectedItems.add(position);
                        } else {
                            userSelectedItems.remove(Integer.valueOf(position));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder categories = new StringBuilder();
                        for (Integer userSelectedItem : userSelectedItems) {
                            categories.append(categoryItems[userSelectedItem]);
                            Category category = MainActivity.controller.findCategoryByName(categoryItems[userSelectedItem]);
                            food.getCategories().add(category);
                            if(userSelectedItems.indexOf(userSelectedItem) != userSelectedItems.size() - 1) {
                                categories.append(", ");
                            }
                        }
                        tvCategory.setText(categories.toString());
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        Arrays.fill(checkedItems, false);
                        userSelectedItems.clear();
                        food.getCategories().clear();
                        tvCategory.setText("");
                    }
                });

                AlertDialog categoryDialog = mBuilder.create();
                categoryDialog.show();
            }
        });

        this.food = new Food(null);
    }

    public void recipeButtonTaped(View view) {
        EditText nameText = findViewById(R.id.editTextTextFoodName);
        EditText descriptionText = findViewById(R.id.editTextFoodDescription);
        if(!nameText.getText().toString().equals("")) {
            food.setName(nameText.getText().toString());
        }
        if (!descriptionText.getText().toString().equals("")) {
            food.setDescription(descriptionText.getText().toString());
        }
        //another if is needed for category and another for medias
        RecipeActivity.setFood(food);
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    public void doneButtonTaped(View view) {
        EditText nameText = findViewById(R.id.editTextTextFoodName);
        EditText descriptionText = findViewById(R.id.editTextFoodDescription);
        food.setName(nameText.getText().toString());
        food.setDescription(descriptionText.getText().toString());
        if (food.getName().equals("")) {

        }
    }


    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}