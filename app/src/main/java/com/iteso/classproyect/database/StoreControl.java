package com.iteso.classproyect.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.classproyect.beans.City;
import com.iteso.classproyect.beans.Store;

import java.util.ArrayList;

/**
 * Created by inqui on 21/03/2018.
 */

public class StoreControl {

    public StoreControl(){}

    public void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",store.getName());
        values.put("phone",store.getPhone());
        values.put("idcity",store.getCity().getId());
        values.put("thumbnail",store.getThumbnail());
        values.put("latitude",store.getLatitude());
        values.put("longitude",store.getLongitude());

        db.insert("Store",null,values);
        try {
            db.close();
        }catch (Exception e){}
        db = null;
        values = null;
    }

    public ArrayList<Store> getStores(DataBaseHandler dh){
        ArrayList<Store> stores = new ArrayList<>();
        Store store;
        String select = "SELECT id," +
                " name," +
                " phone," +
                " idcity," +
                " thumbnail," +
                " latitude," +
                " longitude" +
                " FROM Store";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        while (cursor.moveToNext()){
            store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            //provisional method
            City city = getCityById(cursor.getInt(3),dh);
            store.setCity(city);
            //******************
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));
            stores.add(store);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return stores;
    }

    public Store getStoreById (int idStore, DataBaseHandler dh){
        Store store = null;
        SQLiteDatabase db = dh.getReadableDatabase();
        String select = "SELECT id," +
                " name," +
                " phone," +
                " idcity," +
                " thumbnail," +
                " latitude," +
                " longitude" +
                " FROM Store" +
                " WHERE id = "+idStore;
        Cursor cursor = db.rawQuery(select,null);
        if (cursor != null){
            cursor.moveToFirst();
            store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            //provisional method
            City city = getCityById(cursor.getInt(3),dh);
            store.setCity(city);
            //******************
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){}
        cursor = null;
        db = null;
        return store;
    }

    public City getCityById(int idCity, DataBaseHandler dh){
        City city = null;
        SQLiteDatabase db = dh.getReadableDatabase();
        String select =
                "SELECT id, name FROM City WHERE id = " + idCity;
        Cursor cursor = db.rawQuery(select , null);
        if (cursor.moveToFirst()){
            city = new City();
            city.setId(idCity);
            city.setName(cursor.getString(1));
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){}
        cursor = null;
        db = null;
        return city;
    }
    public void addCity(City city, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",city.getId());
        values.put("name",city.getName());


        db.insert("City",null,values);
        try {
            db.close();
        }catch (Exception e){}
        db = null;
        values = null;
    }

    public Store getStoreByProductId(int prodId, DataBaseHandler dh){
        Store store = null;
        int idstore = 0;
        SQLiteDatabase db = dh.getReadableDatabase();
        String select =
                "SELECT idstore FROM StoreProduct WHERE idproduct = " + prodId;
        Cursor cursor = db.rawQuery(select , null);
        if (cursor.moveToFirst()){
            idstore = cursor.getInt(0);
            store = getStoreById(idstore,dh);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return store;
    }
}
