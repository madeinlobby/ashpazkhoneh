package com.example.ashpazbashi.localDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ashpazbashi.views.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food.db";
    public static final String TABLE_NAME = "food_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "categories";
    public static final String COL_4 = "description";
    public static final String COL_5 = "ingredients";
    public static final String COL_6 = "recipe";
    public static final String COL_7 = "picAddress";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,CATEGORIES TEXT,DESCRIPTION TEXT,INGREDIENTS TEXT,RECIPE TEXT,PICADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String categories, String description,
                              String ingredients, String recipe, String picAddress) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, categories);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, ingredients);
        contentValues.put(COL_6, recipe);
        contentValues.put(COL_7, picAddress);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
    }

    public boolean updateData(String id, String name, String categories, String description,
                              String ingredients, String recipe, String picAddress) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, categories);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, ingredients);
        contentValues.put(COL_6, recipe);
        contentValues.put(COL_7, picAddress);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }
}
