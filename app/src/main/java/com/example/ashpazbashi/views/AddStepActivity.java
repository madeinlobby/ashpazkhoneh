package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;
import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.recipe.Recipe;
import com.example.ashpazbashi.models.recipe.Step;
import com.example.ashpazbashi.views.mediaView.ImageFocusActivity;
import com.example.ashpazbashi.views.recyclerViewAdaptors.PicAdaptor;

import static com.example.ashpazbashi.views.AddFoodActivity.hasPermissions;

public class AddStepActivity extends AppCompatActivity implements PicAdaptor.OnPicListener {

    private static Recipe recipe;
    private static Step step;
    EditText subjectField;
    EditText descriptionField;
    Button addPicBtn;
    PicAdaptor picAdaptor;
    public static final int FILEPICKER_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_step);

        addPicBtn = findViewById(R.id.addStepAddPicBtn);
        addPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if(hasPermissions(AddStepActivity.this, PERMISSIONS)){
                    ShowFilePicker();
                } else {
                    ActivityCompat.requestPermissions(AddStepActivity.this, PERMISSIONS, FILEPICKER_PERMISSIONS);
                }
            }
        });

        step = new Step(null,null, recipe.getFoodName());

        RecyclerView recyclerView = findViewById(R.id.addStepPicRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        picAdaptor = new PicAdaptor(step.getPicAddress(), this, this);
        recyclerView.setAdapter(picAdaptor);
    }

    public void ShowFilePicker(){
        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(AddStepActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .build();
        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                MainActivity.controller.addStepPicIndex(step, path, 0);
                picAdaptor.notifyItemInserted(0);
            }
        });
        chooser.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == FILEPICKER_PERMISSIONS) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        AddStepActivity.this,
                        "Permission granted! Please click on pick a file once again.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        AddStepActivity.this,
                        "Permission denied to read your External storage :(",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
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
            step.setSubject(subjectField.getText().toString());
            step.setDescription(descriptionField.getText().toString());
            MainActivity.controller.addStepToRecipe(step, recipe);
            Intent intent = new Intent(this, RecipeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public static Recipe getRecipe() {
        return recipe;
    }

    public static void setRecipe(Recipe recipe) {
        AddStepActivity.recipe = recipe;
    }

    @Override
    public void click(int position) {
        ImageFocusActivity.setImgPath(step.getPicAddress().get(position));
        Intent intent = new Intent(this, ImageFocusActivity.class);
        startActivity(intent);
    }
}