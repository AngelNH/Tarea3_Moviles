package com.iteso.classproyect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.classproyect.beans.ItemProduct;

import java.util.ArrayList;
import java.util.Iterator;


public class Fragment_Technology extends android.support.v4.app.Fragment {


    public Fragment_Technology() {
        // Required empty public constructor
    }
    ArrayList<ItemProduct> products;
    RecyclerView.Adapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        //ArrayList<ItemProduct> products = new ArrayList<ItemProduct>();
        products = new ArrayList<>();
        products.add(new ItemProduct("Mac", "BestBuy", "+52 3334564759", "Best Buy Plaza Ciudadela ", 0,1));
        products.add(new ItemProduct("AlienWare de Miguel", "DELL", "+52 3318275480", "DELL, Punto de Venta zapopan", 1,2));
        products.add(new ItemProduct("Lanix", "Saint Johny", "+52 3321349087", "Mercado San Juan de Dios, Punto de venta", 2,3));


        adapter = new AdapterProduct(getActivity(), products,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ItemProduct itprod;

        ItemProduct item = data.getParcelableExtra("ITEM");

        Iterator<ItemProduct> ite = this.products.iterator();
        int i = 0;
        Log.e("RESULT","Entered the technology activityresult.");
        while (ite.hasNext()){
            itprod = ite.next();
            if (itprod.getCode() == item.getCode()){
                products.set(i,item);
                Log.e("RESULT","the product is set"+i+" code: "+item.getCode());
                break;
            }
            i++;
        }
        adapter.notifyDataSetChanged();
        //products.add(item);//check this line.
    }
}
