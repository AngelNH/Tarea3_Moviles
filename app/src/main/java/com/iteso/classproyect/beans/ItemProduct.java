package com.iteso.classproyect.beans;

/**
 * Created by inqui on 26/02/2018.
 */

public class ItemProduct {
    private String title;
    private String store;
    private String location;
    private String phone;
    private int image;


    public ItemProduct(String title, String store, String location, String phone, int image) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.image = image;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
