package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.recipe.Step;

public class EditStepActivity extends AppCompatActivity {

    private static Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_step);
    }

    public static Step getStep() {
        return step;
    }

    public static void setStep(Step step) {
        EditStepActivity.step = step;
    }
}