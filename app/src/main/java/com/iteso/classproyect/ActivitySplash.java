package com.iteso.classproyect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.classproyect.beans.City;
import com.iteso.classproyect.beans.Store;
import com.iteso.classproyect.beans.User;
import com.iteso.classproyect.database.DataBaseHandler;
import com.iteso.classproyect.database.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //timer task: que tarea se va a hacer en cierto tiempo
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadUser();
                loadStores();
                Intent intent;
                if (user.isLogged()){
                    intent = new Intent(ActivitySplash.this,Activity_main.class);
                }else{
                    intent = new Intent(ActivitySplash.this,ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,2000);//se define el tiempo.

    }
    public User loadUser(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.USER_PREFERENCES",MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME",null));
        user.setPassword(sharedPreferences.getString("PWD",null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED",false));
        sharedPreferences = null;
        return user;
    }

    public void loadStores(){
        DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplash.this);
        StoreControl storeControl = new StoreControl();
        ArrayList<Store> stores = storeControl.getStores(dh);
        if (stores.isEmpty()){
            //Create Cities
            City gdl = new City(1,"Guadalajara");
            City zap = new City(2,"Zapopan");
            City tla = new City(3,"Tlaquepaque");
            //Create the stores
            Store bestbuy = new Store(1,"BestBuy","3323436570",1,45.232,32.4324,gdl);
            Store homedepot = new Store(2,"Home Depot","3423123456",2,54.545,32.5456,zap);
            Store liverpool = new Store(3,"Liverpool","3456324456",3,54.678,32.5643,tla);
            //add stores into the database.
            storeControl.addStore(bestbuy,dh);
            storeControl.addStore(homedepot,dh);
            storeControl.addStore(liverpool,dh);
            //add cities into the database
            storeControl.addCity(gdl,dh);
            storeControl.addCity(zap,dh);
            storeControl.addCity(tla,dh);
        }
    }
}
