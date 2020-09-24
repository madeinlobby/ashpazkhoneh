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
import android.widget.TextView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;
import com.example.ashpazbashi.R;
import com.example.ashpazbashi.controllers.MainController;
import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.views.mediaView.ImageFocusActivity;
import com.example.ashpazbashi.views.recyclerViewAdaptors.PicAdaptor;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.ashpazbashi.views.AddFoodActivity.hasPermissions;

public class EditFoodActivity extends AppCompatActivity implements PicAdaptor.OnPicListener{

    private static Food food;
    EditText nameField;
    Button addCategory;
    Button addPicBtn;
    String id;
    TextView tvCategory;
    EditText descriptionField;
    String[] categoryItems;
    boolean[] checkedItems;
    PicAdaptor picAdaptor;
    public static final int FILEPICKER_PERMISSIONS = 1;
    ArrayList<Integer> userSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        if(getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }

        RecyclerView recyclerView = findViewById(R.id.editFoodAddPicRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        picAdaptor = new PicAdaptor(food.getPicsAddress(), this, this);
        recyclerView.setAdapter(picAdaptor);

        nameField = findViewById(R.id.editTextEditFoodName);
        descriptionField = findViewById(R.id.editTextEditFoodDescription);

        nameField.setText(food.getName());
        descriptionField.setText(food.getDescription());

        addCategory = findViewById(R.id.editFoodCategoryButton);
        addPicBtn = findViewById(R.id.editFoodPicBtn);
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

        addPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if(hasPermissions(EditFoodActivity.this, PERMISSIONS)){
                    ShowFilePicker();
                } else{
                    ActivityCompat.requestPermissions(EditFoodActivity.this, PERMISSIONS, FILEPICKER_PERMISSIONS);
                }
            }
        });

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
                            food.addCategory(category);
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

    public void ShowFilePicker(){
        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(EditFoodActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .build();
        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                MainActivity.controller.addPicIndex(food, path, 0);
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
                        EditFoodActivity.this,
                        "Permission granted! Please click on pick a file once again.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        EditFoodActivity.this,
                        "Permission denied to read your External storage :(",
                        Toast.LENGTH_SHORT
                ).show();
            }

            return;
        }
    }

    public void editFoodRecipeButtonTapped(View view) {
        food.setName(nameField.getText().toString());
        food.setDescription(descriptionField.getText().toString());
        Intent intent = new Intent(this, RecipeActivity.class);
        RecipeActivity.setFood(food);
        startActivity(intent);
    }

    public void editFoodDoneButton(View view) {
        food.setName(nameField.getText().toString());
        food.setDescription(descriptionField.getText().toString());
        if (food.getName().equals("")) {
            AlertDialog.Builder errorBuilder = new AlertDialog.Builder(EditFoodActivity.this);
            View customLayout = getLayoutInflater().inflate(R.layout.food_name_error, null);
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
            Intent intent = new Intent(this, MainActivity.class);
            MainActivity.controller.updateRowDB(MainActivity.myDb, food, id);
            startActivity(intent);
        }
    }

    public void editFoodDeleteButton(View view) {
        food.setName(nameField.getText().toString());
        food.setDescription(descriptionField.getText().toString());
        AlertDialog.Builder deleteWarn = new AlertDialog.Builder(EditFoodActivity.this);
        View customLayout = getLayoutInflater().inflate(R.layout.delete_food_warning, null);
        deleteWarn.setView(customLayout);
        deleteWarn.setTitle(R.string.warning);
        deleteWarn.setCancelable(false);
        deleteWarn.setPositiveButton(R.string.yes_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.controller.removeFood(food);
                Intent intent = new Intent(String.valueOf(MainActivity.class));
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

    public static Food getFood() {
        return food;
    }

    public static void setFood(Food food) {
        EditFoodActivity.food = food;
    }

    @Override
    public void click(int position) {
        ImageFocusActivity.setImgPath(food.getPicsAddress().get(position));
        Intent intent = new Intent(this, ImageFocusActivity.class);
        startActivity(intent);
    }
}