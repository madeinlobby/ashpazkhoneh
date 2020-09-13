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
import com.example.ashpazbashi.models.recipe.Step;
import com.example.ashpazbashi.views.mediaView.ImageFocusActivity;
import com.example.ashpazbashi.views.recyclerViewAdaptors.PicAdaptor;

import static com.example.ashpazbashi.views.AddFoodActivity.hasPermissions;

public class EditStepActivity extends AppCompatActivity implements PicAdaptor.OnPicListener{

    private static Step step;
    EditText subjectField;
    EditText descriptionField;
    Button addPicBtn;
    PicAdaptor picAdaptor;
    public static final int FILEPICKER_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_step);

        subjectField = findViewById(R.id.editTextEditStepSubject);
        descriptionField = findViewById(R.id.editTextEditStepDescription);

        subjectField.setText(step.getSubject());
        descriptionField.setText(step.getDescription());
        addPicBtn = findViewById(R.id.editStepAddPicBtn);

        addPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if(hasPermissions(EditStepActivity.this, PERMISSIONS)){
                    ShowFilePicker();
                } else {
                    ActivityCompat.requestPermissions(EditStepActivity.this, PERMISSIONS, FILEPICKER_PERMISSIONS);
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.editStepRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        picAdaptor = new PicAdaptor(step.getPicAddress(), this, this);
        recyclerView.setAdapter(picAdaptor);
    }

    public void ShowFilePicker(){
        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(EditStepActivity.this)
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
                        EditStepActivity.this,
                        "Permission granted! Please click on pick a file once again.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        EditStepActivity.this,
                        "Permission denied to read your External storage :(",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
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

    @Override
    public void click(int position) {
        ImageFocusActivity.setImgPath(step.getPicAddress().get(position));
        Intent intent = new Intent(this, ImageFocusActivity.class);
        startActivity(intent);
    }
}