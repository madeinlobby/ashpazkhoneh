package com.example.ashpazbashi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.Context;
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
import com.example.ashpazbashi.models.Category;
import com.example.ashpazbashi.models.Food;
import com.example.ashpazbashi.models.recipe.Recipe;
import com.example.ashpazbashi.views.mediaView.ImageFocusActivity;
import com.example.ashpazbashi.views.recyclerViewAdaptors.PicAdaptor;

import java.util.ArrayList;
import java.util.Arrays;


public class AddFoodActivity extends AppCompatActivity implements PicAdaptor.OnPicListener{

    private Food food;
    private Recipe recipe;
    String[] categoryItems;
    boolean[] checkedItems;
    PicAdaptor picAdaptor;
    Button addCategory;
    Button addMedia;
    TextView tvCategory;
    ArrayList<Integer> userSelectedItems = new ArrayList<>();
    public static final int FILEPICKER_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        addCategory = findViewById(R.id.addFoodCategoryButton);
        addMedia = findViewById(R.id.addMediaButtonAddFood);
        tvCategory = findViewById(R.id.foodCategoryTv);

        categoryItems = getResources().getStringArray(R.array.food_category_items);
        checkedItems = new boolean[categoryItems.length];

        addMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] PERMISSIONS = {
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if(hasPermissions(AddFoodActivity.this, PERMISSIONS)){
                    ShowFilePicker();
                } else{
                    ActivityCompat.requestPermissions(AddFoodActivity.this, PERMISSIONS, FILEPICKER_PERMISSIONS);
                }
            }
        });

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
        this.recipe = new Recipe(food);
        this.food.setRecipe(recipe);

        RecyclerView recyclerView = findViewById(R.id.addFoodPicRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        picAdaptor = new PicAdaptor(food.getPicsAddress(), this, this);
        recyclerView.setAdapter(picAdaptor);
    }

    public void ShowFilePicker(){
        final StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(AddFoodActivity.this)
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

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == FILEPICKER_PERMISSIONS) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        AddFoodActivity.this,
                        "Permission granted! Please click on pick a file once again.",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        AddFoodActivity.this,
                        "Permission denied to read your External storage :(",
                        Toast.LENGTH_SHORT
                ).show();
            }

            return;
        }
    }


    public void recipeButtonTaped(View view) {
        EditText nameText = findViewById(R.id.editTextTextFoodName);
        EditText descriptionText = findViewById(R.id.editTextFoodDescription);

        food.setName(nameText.getText().toString());
        food.setDescription(descriptionText.getText().toString());

        Intent intent = new Intent(this, RecipeActivity.class);
        RecipeActivity.setFood(food);
        startActivity(intent);
    }

    public void doneButtonTaped(View view) {
        EditText nameText = findViewById(R.id.editTextTextFoodName);
        EditText descriptionText = findViewById(R.id.editTextFoodDescription);
        food.setName(nameText.getText().toString());
        food.setDescription(descriptionText.getText().toString());
        if (food.getName().equals("")) {
            AlertDialog.Builder errorBuilder = new AlertDialog.Builder(AddFoodActivity.this);
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
            startActivity(intent);
        }
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void ingredientButtonTapped(View view) {
        //here we should use a startActivityForResult
        //will be reformed

        //point : ghazie cancele chon listo az food midim :)
        Intent intent = new Intent(this, IngredientsActivity.class);
        IngredientsActivity.setFood(food);
        startActivity(intent);
    }

    @Override
    public void click(int position) {
        ImageFocusActivity.setImgPath(food.getPicsAddress().get(position));
        Intent intent = new Intent(this, ImageFocusActivity.class);
        startActivity(intent);
    }
}