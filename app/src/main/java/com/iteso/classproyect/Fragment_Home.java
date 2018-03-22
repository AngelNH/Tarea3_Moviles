package com.iteso.classproyect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.classproyect.beans.ItemProduct;
import com.iteso.classproyect.database.DataBaseHandler;
import com.iteso.classproyect.database.ItemProductControl;

import java.util.ArrayList;


//CATEGORY 2
public class Fragment_Home extends android.support.v4.app.Fragment {
    public Fragment_Home(){

    }
    ArrayList<ItemProduct> products;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        //ArrayList<ItemProduct> products = new ArrayList<>();
        /*
        products = new ArrayList<>();
        products.add(new ItemProduct("KingBed, Sealy","Soriana San Isidro","+52 3323453422","Carretera Tesistan, Periferico Norte", 3,6));
        products.add(new ItemProduct("Wissen Couch","Palacio de Hierro", "+52 3345632122","Av. Patria, Plaza Andares", 4,7));
        */
        //-----------------------------------------------------
        ItemProductControl control = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        products = control.getItemProductsByCategory(2,dh);
        //-----------------------------------------------------

        adapter = new AdapterProduct(getActivity(), products,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1111:
                if (requestCode == Activity.RESULT_OK){
                    ItemProduct item = data.getParcelableExtra("ITEM");
                    if (item != null){
                        products.add(item);//check this line.
                    }

                }
        }
    }
}
