package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditFoodActivity extends AppCompatActivity {

    private static Food food;
    EditText nameField;
    Button addCategory;
    TextView tvCategory;
    EditText descriptionField;
    String[] categoryItems;
    boolean[] checkedItems;
    ArrayList<Integer> userSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        nameField = findViewById(R.id.editTextEditFoodName);
        descriptionField = findViewById(R.id.editTextEditFoodDescription);

        nameField.setText(food.getName());
        descriptionField.setText(food.getDescription());

        addCategory = findViewById(R.id.editFoodCategoryButton);
        tvCategory = findViewById(R.id.editCategoryTv);

        categoryItems = getResources().getStringArray(R.array.food_category_items);
        checkedItems = new boolean[categoryItems.length];
        StringBuilder editCategoryString = new StringBuilder();
        for (int i = 0; i < categoryItems.length; i++) {
            if (food.hasCategory(categoryItems[i])) {
                checkedItems[i] = true;
                userSelectedItems.add(i);
                editCategoryString.append(categoryItems[i]).append(",");
            }
        }

        tvCategory.setText(editCategoryString.toString());

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditFoodActivity.this);
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
    }


    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        EditFoodActivity.food = food;
    }
}