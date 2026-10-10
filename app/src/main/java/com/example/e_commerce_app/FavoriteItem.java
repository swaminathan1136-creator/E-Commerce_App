package com.example.e_commerce_app;

public class FavoriteItem {
    public String name;
    public String price;
    public String restaurant;
    public float rating;
    public int imageResource;

    public FavoriteItem(String name, String price, String restaurant, float rating, int imageResource) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.rating = rating;
        this.imageResource = imageResource;
    }
}