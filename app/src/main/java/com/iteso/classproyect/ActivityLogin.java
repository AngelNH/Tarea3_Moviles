package com.iteso.classproyect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.activity_login_user);
        password = findViewById(R.id.activity_login_password);
        login = findViewById(R.id.activity_login_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
                Intent intent = new Intent(ActivityLogin.this, Activity_main.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.USER_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", username.getText().toString());
        editor.putString("PWD", username.getText().toString());
        editor.putBoolean("LOGGED", true);
        editor.apply();
        //apply es sincrono
        //commit es asincrono
    }
}
