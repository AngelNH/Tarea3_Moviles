package com.iteso.classproyect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iteso.classproyect.beans.ItemProduct;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by inqui on 26/02/2018.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{
    ArrayList<ItemProduct> products;
    public AdapterProduct(ArrayList<ItemProduct>products){

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public TextView mStrore;
        public TextView mLocation;
        public TextView mPhone;
        public ImageView mImage;


        public ViewHolder(View v){
            super(v);
            mTitle = v.findViewById(R.id.item_product_title);
            mStrore = v.findViewById(R.id.item_product_store);
            mLocation = v.findViewById(R.id.item_product_location);
            mPhone = v.findViewById(R.id.item_product_phone);
            mImage = v.findViewById(R.id.item_product_image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(products.get(position).getTitle());
        holder.mStrore.setText(products.get(position).getStore());
        holder.mLocation.setText((products.get(position).getLocation()));
        holder.mPhone.setText((products.get(position).getPhone()));
        int imageid = 0;
        switch (products.get(position).getImage()){
            case 1:
                imageid = R.drawable.mac;
                break;
            case 2:
                imageid = R.drawable.mac;
                break;
            case 3:
                imageid = R.drawable.mac;
                break;
        }

        holder.mImage.setImageResource(imageid);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
