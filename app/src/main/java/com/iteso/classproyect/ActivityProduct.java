package com.iteso.classproyect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.classproyect.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    EditText title, store, location,phone;
    ImageView image;
    Button save, cancel;
    ItemProduct item;
    ItemProduct item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //Edit texts
        title = findViewById(R.id.activity_product_title);
        store = findViewById(R.id.activity_product_store);
        location = findViewById(R.id.activity_product_location);
        phone = findViewById(R.id.activity_product_phone);
        //image view
        image = findViewById(R.id.activity_product_image);
        //Buttons
        save = findViewById(R.id.activity_product_save);
        cancel = findViewById(R.id.activity_product_cancel);

        //obtain the extras of the intent.
        if (getIntent().getExtras() != null){
            item = getIntent().getParcelableExtra("ITEM");
            if (item != null){
                title.setText(item.getTitle());
                store.setText(item.getStore());
                location.setText(item.getLocation());
                phone.setText(item.getPhone());
            }
        }

        //set the click listener for each button.
        //case button save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item2 = new ItemProduct(title.getText().toString(),
                                        store.getText().toString(),
                                        location.getText().toString(),
                                        phone.getText().toString(),
                                        item.getCode());//TODO with image
                //Intent intent = new Intent();
                //intent.putExtra("ITEM",item2);
                //setResult(RESULT_OK,intent);
                finish();
            }
        });
        //case button cancel
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
