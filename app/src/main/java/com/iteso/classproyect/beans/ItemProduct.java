package com.iteso.classproyect.beans;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by inqui on 26/02/2018.
 */

public class ItemProduct  implements Parcelable{
    private int code;
    private String title;
    private String description;
    private int imageNumber;
    private Store store;
    private Category category;


    public ItemProduct(){}

    //Constructor for the database.

    public ItemProduct(int code, String title, String description, int imageNumber, Store store, Category category) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.imageNumber = imageNumber;
        this.category = category;
        this.store = store;
    }
    //Actual Constructor
    /*
    public ItemProduct(String title, String storeName,String phone, String location, int imageNumber, int code) {
        this.title = title;
        this.storeName = storeName;
        this.location = location;
        this.phone = phone;
        this.imageNumber = imageNumber;
        this.code = code;

    }*/

    /*
    //check the parcelable
    public ItemProduct(Parcel in){
        title = in.readString();
        storeName = in.readString();
        location = in.readString();
        phone = in.readString();
        imageNumber=in.readInt();
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
        dest.writeString(storeName);
        dest.writeString(location);
        dest.writeString(phone);
        dest.writeInt(imageNumber);
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
    */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageNumber=" + imageNumber +
                ", store=" + store +
                ", category=" + category +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.imageNumber);
        dest.writeParcelable(this.store, flags);
        dest.writeParcelable(this.category, flags);

    }

    protected ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.imageNumber = in.readInt();
        this.store = in.readParcelable(Store.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());

    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };
}
