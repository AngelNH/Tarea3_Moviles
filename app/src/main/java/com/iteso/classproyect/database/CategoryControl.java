package com.iteso.classproyect.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.classproyect.beans.Category;

import java.util.ArrayList;

/**
 * Created by inqui on 21/03/2018.
 */

public class CategoryControl {

    public CategoryControl(){
    }
    public ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        Category category ;
        String select = "SELECT id, name FROM Category";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        while (cursor.moveToNext()){
            category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){

        }
        db = null;
        cursor = null;
        return categories;
    }
}
