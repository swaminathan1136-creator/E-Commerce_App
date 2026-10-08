package com.example.e_commerce_app;

public class FoodItem {

    public String name;
    public String restaurant;
    public String price;
    public float rating;
    public int deliveryTime; // in minutes
    public boolean isPureVeg;
    public boolean hasOffer;
    public int imageResource;

    public FoodItem(String name, String restaurant, String price, float rating,
                    int deliveryTime, boolean isPureVeg, boolean hasOffer, int imageResource) {
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.isPureVeg = isPureVeg;
        this.hasOffer = hasOffer;
        this.imageResource = imageResource;
    }
}