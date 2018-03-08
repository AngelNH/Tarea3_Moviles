package com.iteso.classproyect.beans;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by inqui on 26/02/2018.
 */

public class ItemProduct  implements Parcelable{
    private String title;
    private String store;
    private String phone;
    private String location;
    private Drawable image;

    private int code;


    public ItemProduct(String title, String store,String phone, String location) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;

    }
    public ItemProduct(String title, String store,String phone, String location, Drawable image) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.image = image;

    }
    public ItemProduct(String title, String store,String phone, String location, Drawable image, int code) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.image = image;
        this.code = code;

    }
    public ItemProduct(String title, String store,String phone, String location, int code) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.code = code;

    }
    public ItemProduct(Parcel in){
        title = in.readString();
        store = in.readString();
        location = in.readString();
        phone = in.readString();
        code = in.readInt();
    }
    //Overide methods for the parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(store);
        dest.writeString(location);
        dest.writeString(phone);
        dest.writeInt(code);
    }

    public static final Parcelable.Creator<ItemProduct> CREATOR =
            new Parcelable.Creator<ItemProduct>(){
                @Override
                public ItemProduct createFromParcel(Parcel source) {
                    return new ItemProduct(source);
                }

                @Override
                public ItemProduct[] newArray(int size) {
                    return new ItemProduct[size];
                }
            };
    //**********************************
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
    @Override
    public String toString() {
        return "ItemProduct{" +
                "title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + store + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
