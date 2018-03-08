package com.iteso.classproyect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.classproyect.beans.ItemProduct;

import java.util.ArrayList;


public class Fragment_Electronics extends android.support.v4.app.Fragment {

    public Fragment_Electronics() {
        // Required empty public constructor
    }
    ArrayList<ItemProduct> electronics;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        //ArrayList<ItemProduct> electronics = new ArrayList<>();
        electronics = new ArrayList<>();
        electronics.add(new ItemProduct("Whirpool EasyWash","Liverpool","+52 4432435678","Plaza Galerías Guadalajara", 6,4));
        electronics.add(new ItemProduct("Beats Stereo Plus","Fabricas de Francia", "+52 2343234567","La Gran Plaza Fashion Mall Guadalajara", 5,5));

        adapter = new AdapterProduct(getActivity(), electronics,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2222:
                if (resultCode == Activity.RESULT_OK){
                    ItemProduct item = data.getParcelableExtra("ITEM");
                    if (item != null){
                        electronics.add(item);//check this line.
                    }

                }
        }
    }
}
