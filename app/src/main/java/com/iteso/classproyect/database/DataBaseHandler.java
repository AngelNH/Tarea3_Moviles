package com.iteso.classproyect.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by inqui on 20/03/2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper{
    private static String DATABASE_NAME = "ITESO_Store.db";
    private static int DATABASE_VERSION = 2;    //--------- VERSION

    private static DataBaseHandler dataBaseHandler;//Singleton

    private DataBaseHandler(Context context){
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public static DataBaseHandler getInstance(Context context){
        if (dataBaseHandler == null)
            dataBaseHandler = new DataBaseHandler(context);
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //City Table
        //id    INTEGER
        //name  TEXT
        String cityTable =
                "CREATE TABLE City (id INTEGER PRIMARY KEY, name TEXT)";
        //Category Table
        //id    INTEGER
        //name  TEXT
        String categoryTable =
                "CREATE TABLE Category (id INTEGER PRIMARY KEY, name TEXT)";
        //Store Table
        //id        INTEGER
        //name      TEXT
        //phone     TEXT
        //idcity    INTEGER
        //thumbnail INTEGER
        //latitude  DOUBLE
        //longitude DOUBLE
        String storeTable =
                "CREATE TABLE Store (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "name TEXT, " +
                                    "phone TEXT, " +
                                    "idcity INTEGER, " +
                                    "thumbnail INTEGER, " +
                                    "latitude DOUBLE, " +
                                    "longitude DOUBLE) ";
        //Product Table
        //idproduct INTEGER
        //title     TEXT
        //image     INTEGER
        //idcategory INTEGER
        String productTable =
                "CREATE TABLE Product (idproduct INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "title TEXT, " +
                                        "image INTEGER, " +
                                        "idcategory INTEGER)";
        //StoreProduct Table
        //id        INTEGER
        //idproduct INTEGER
        //idstore   INTEGER
        String storeProductTable =
                "CREATE TABLE StoreProduct (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        " idproduct INTEGER," +
                                        " idstore INTEGER)";

        String addElectronics =
                        "INSERT INTO Category ( id , name ) " +
                        "VALUES (1 , Electronics)";
        String addHome =
                        "INSERT INTO Category ( id , name ) " +
                        "VALUES (2 , Home)";
        String addTechnology =
                        "INSERT INTO Category ( id , name ) " +
                        "VALUES (3 , Technology)";

        db.execSQL(cityTable);
        db.execSQL(categoryTable);
        db.execSQL(storeTable);
        db.execSQL(productTable);
        db.execSQL(storeProductTable);
        insert(db);
        upgradeV2(db);

        //añadir las demás actualizaciónes.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        switch (oldV){
            case 1: upgradeV2(db);

        }
        upgradeV2(db);
    }
    public void insert(SQLiteDatabase db){
        //Insertions
        String addElectronics =
                "INSERT INTO Category ( id , name ) " +
                        "VALUES (1 , 'Electronics')";
        String addHome =
                "INSERT INTO Category ( id , name ) " +
                        "VALUES (2 , 'Home')";
        String addTechnology =
                "INSERT INTO Category ( id , name ) " +
                        "VALUES (3 , 'Technology')";
        db.execSQL(addElectronics);
        db.execSQL(addHome);
        db.execSQL(addTechnology);
    }
    public void upgradeV2(SQLiteDatabase db){
        String drop = "DROP TABLE Store";
        db.execSQL(drop);
        String storeTable =
                "CREATE TABLE Store (id INTEGER PRIMARY KEY , " +
                        "name TEXT, " +
                        "phone TEXT, " +
                        "idcity INTEGER, " +
                        "thumbnail INTEGER, " +
                        "latitude DOUBLE, " +
                        "longitude DOUBLE) ";
        db.execSQL(storeTable);
    }

}
