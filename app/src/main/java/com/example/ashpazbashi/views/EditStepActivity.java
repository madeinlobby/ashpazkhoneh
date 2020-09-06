package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.recipe.Step;

public class EditStepActivity extends AppCompatActivity {

    private static Step step;
    EditText subjectField;
    EditText descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_step);

        subjectField = findViewById(R.id.editTextEditStepSubject);
        descriptionField = findViewById(R.id.editTextEditStepDescription);
    }

    public void editStepDoneButtonTapped(View view) {
        step.setSubject(subjectField.getText().toString());
        step.setDescription(descriptionField.getText().toString());

        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    public void editStepDeleteButtonTapped(View view) {
        step.setSubject(subjectField.getText().toString());
        step.setDescription(descriptionField.getText().toString());

        AlertDialog.Builder deleteWarn = new AlertDialog.Builder(EditStepActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.delete_step_warning, null);
        deleteWarn.setView(customLayout);
        deleteWarn.setTitle(R.string.warning);
        deleteWarn.setCancelable(false);
        deleteWarn.setPositiveButton(R.string.yes_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.controller.removeStep(step);
                Intent intent = new Intent(String.valueOf(RecipeActivity.class));
                startActivity(intent);
            }
        });
        deleteWarn.setNegativeButton(R.string.no_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog warnDialog = deleteWarn.create();
        warnDialog.show();
    }

    public static Step getStep() {
        return step;
    }

    public static void setStep(Step step) {
        EditStepActivity.step = step;
    }
}