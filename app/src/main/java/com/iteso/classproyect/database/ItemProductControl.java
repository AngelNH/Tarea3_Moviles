package com.iteso.classproyect.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.classproyect.beans.Category;
import com.iteso.classproyect.beans.ItemProduct;

import java.util.ArrayList;

/**
 * Created by inqui on 21/03/2018.
 */

public class ItemProductControl {

    public ItemProductControl(){}

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();

        ContentValues productValues = new ContentValues();
        productValues.put("title",itemProduct.getTitle());
        productValues.put("image",itemProduct.getImageNumber());
        productValues.put("idcategory",itemProduct.getCategory().getId());
        //insert into Product table
        db.insert("Product",null,productValues);

        ContentValues storeProductValues = new ContentValues();
        storeProductValues.put("idproduct",itemProduct.getCode());
        storeProductValues.put("idstore",itemProduct.getStore().getId());
        //insert into StoreProduct table
        db.insert("StoreProduct",null,storeProductValues);
        try {
            db.close();
        }catch (Exception e){

        }
        db = null;
        storeProductValues = null;
        productValues = null;
    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();
        // Get the correct category
        CategoryControl control = new CategoryControl();
        Category c = null;
        ArrayList<Category> categories = control.getCategories(dh);
        for (Category category : categories){
            if (category.getId()==idCategory){
                c = category;
                break;
            }
        }
        //**************************
        ItemProduct item;
        SQLiteDatabase db = dh.getReadableDatabase();
        String select =
                "SELECT idproduct, " +
                        "title, " +
                        "image, " +
                        "idcategory " +
                        "FROM Product " +
                        "WHERE idcategory = "+idCategory;
        Cursor cursor = db.rawQuery(select,null);
        while (cursor.moveToNext()){
            item = new ItemProduct();
            int idproduct = cursor.getInt(0);
            item.setCode(idproduct);
            item.setTitle(cursor.getString(1));
            item.setImageNumber(cursor.getInt(2));
            item.setCategory(c);
            //to get the store.
            StoreControl storeControl = new StoreControl();
            item.setStore(storeControl.getStoreByProductId(idproduct,dh));
            //
            products.add(item);
        }
        try {
            cursor.close();//always close first the cursor.
            db.close();
        }catch (Exception e){
        }
        db = null;
        cursor = null;
        return products;
    }


}
