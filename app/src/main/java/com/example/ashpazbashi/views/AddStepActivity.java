package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.recipe.Recipe;
import com.example.ashpazbashi.models.recipe.Step;

public class AddStepActivity extends AppCompatActivity {

    private static Recipe recipe;
    EditText subjectField;
    EditText descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_step);
    }

    public void addStepDoneButtonTapped(View view) {
        subjectField = findViewById(R.id.editTextAddStepSubject);
        descriptionField = findViewById(R.id.editTextAddStepDescription);

        if (subjectField.getText().toString().equals("") || descriptionField.getText().toString().equals("")) {
            AlertDialog.Builder errorBuilder = new AlertDialog.Builder(AddStepActivity.this);
            View customLayout = getLayoutInflater().inflate(R.layout.step_sub_des_error, null);
            errorBuilder.setView(customLayout);
            errorBuilder.setTitle(R.string.error);
            errorBuilder.setCancelable(false);
            errorBuilder.setNegativeButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog errorDialog = errorBuilder.create();
            errorDialog.show();
        } else {
            Step step = new Step(subjectField.getText().toString(),
                    descriptionField.getText().toString(), recipe);
            Intent intent = new Intent(this, RecipeActivity.class);
            startActivity(intent);
        }

    }

    public static Recipe getRecipe() {
        return recipe;
    }

    public static void setRecipe(Recipe recipe) {
        AddStepActivity.recipe = recipe;
    }
}