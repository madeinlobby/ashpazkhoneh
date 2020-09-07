package com.example.ashpazbashi.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.ingredients.Ingredient;
import com.example.ashpazbashi.views.recyclerViewAdaptors.IngredientAdaptor;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity implements IngredientAdaptor.OnIngredientListener {

    //for getting data arrayList from adapter we keep a reference
    static IngredientAdaptor adaptor;
    static Food food;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        RecyclerView recyclerView = findViewById(R.id.ingredientsRecyclerView);
        IngredientAdaptor adapter = new IngredientAdaptor((ArrayList<Ingredient>) food.getIngredients(), this, this);
        IngredientsActivity.adaptor = adapter;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addIngredientClick(View view) {
        //show dialog and get data



        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.add_ingredient_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText name = (EditText) promptsView
                .findViewById(R.id.newIngredientNameEditText);
        final EditText unit = (EditText) promptsView
                .findViewById(R.id.newIngredientUnitEditText);
        final EditText amount = (EditText) promptsView
                .findViewById(R.id.newIngredientAmountEditText);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                String amountStr = amount.getText().toString();
                                String nameStr = name.getText().toString();
                                String unitStr = unit.getText().toString();
                                adaptor.getData().add(new Ingredient(nameStr, amountStr, unitStr));
                                adaptor.notifyDataSetChanged();
                                // chon darim listo az food migirim dige nemikhad
                                //adaptor ro negah darim vali chon momkene farze mahal
                                //liste voroodie adaptor avaz beshe az inja taghiirato
                                //set mikonim ala barekatellah :)
                                //albate nitify niaz be adapter dare fek konam
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }

    @Override
    public void onIngredientItemCLick(int position) {
        // when user clicks on an item
        Toast.makeText(IngredientsActivity.this, "click item " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteIngredientClick(int position) {
        // when user clicks on delete icon on item
        Toast.makeText(IngredientsActivity.this, "click delete " + position, Toast.LENGTH_SHORT).show();
        adaptor.getData().remove(position);
        adaptor.notifyDataSetChanged();
    }


    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        IngredientsActivity.food = food;
    }

    public void doneIngredientClick(View view) {
        if (adaptor.getData().isEmpty()) {
            Toast.makeText(IngredientsActivity.this, "The list is empty!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(IngredientsActivity.this, "The list saved :)", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}