package com.iteso.classproyect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.classproyect.beans.Category;
import com.iteso.classproyect.beans.ItemProduct;
import com.iteso.classproyect.beans.Store;
import com.iteso.classproyect.database.CategoryControl;
import com.iteso.classproyect.database.DataBaseHandler;
import com.iteso.classproyect.database.ItemProductControl;
import com.iteso.classproyect.database.StoreControl;

import java.util.ArrayList;
import java.util.List;

public class ActivityItem extends AppCompatActivity {
    EditText title;
    Button save;
    Spinner categories,stores,image;
    ArrayList<Category> cat;
    ArrayList<Store> sto;
    final DataBaseHandler dh = DataBaseHandler.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        title = findViewById(R.id.activity_item_title);
        categories = findViewById(R.id.activity_item_spinner_category);
        stores = findViewById(R.id.activity_item_spinner_stores);
        save = findViewById(R.id.activity_item_save);
        image = findViewById(R.id.activity_item_spinner_image);


        CategoryControl categoryControl = new CategoryControl();
        StoreControl storeControl = new StoreControl();

        List<String> catName = new ArrayList<>();
        cat = categoryControl.getCategories(dh);
        for (Category c : cat){
            catName.add(c.getName());
        }

        List<String> stoName = new ArrayList<>();
        sto = storeControl.getStores(dh);
        for (Store s : sto){
            stoName.add(s.getName());
        }

        ArrayAdapter<String> catAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,catName);
        ArrayAdapter<String> stoAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,stoName);

        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categories.setAdapter(catAdapter);
        stores.setAdapter(stoAdapter);

        //save the new item.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category1 = cat.get(categories.getSelectedItemPosition());
                Store store1 = sto.get(stores.getSelectedItemPosition());
                ItemProductControl controlItem = new ItemProductControl();
                ItemProduct item = new ItemProduct();
                item.setTitle(title.getText().toString());
                item.setImageNumber(image.getSelectedItemPosition());
                item.setStore(store1);
                item.setCategory(category1);
                controlItem.addItemProduct(item,dh);

                Intent intent = new Intent(ActivityItem.this,Activity_main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
