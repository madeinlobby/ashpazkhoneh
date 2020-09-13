package com.example.ashpazbashi.views.mediaView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ashpazbashi.R;
import com.zolad.zoominimageview.ZoomInImageViewAttacher;

import java.io.File;

public class ImageFocusActivity extends AppCompatActivity {

    ImageView imageView;
    public static String imgPath;

    public static void setImgPath(String imgPath) {
        ImageFocusActivity.imgPath = imgPath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_focus);

        imageView = findViewById(R.id.focusImageView);
        File imgFile = new File(imgPath);

        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }

        ZoomInImageViewAttacher zoomInImageViewAttacher = new ZoomInImageViewAttacher(imageView);


    }
}