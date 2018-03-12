package com.iteso.classproyect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.classproyect.beans.User;

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
}
