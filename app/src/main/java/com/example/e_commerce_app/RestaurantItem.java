package com.example.e_commerce_app;

public class RestaurantItem {

    String name;
    String cuisine;
    String rating;
    String deliveryTime;
    int imageResource;

    public RestaurantItem(
            String name,
            String cuisine,
            String rating,
            String deliveryTime,
            int imageResource
    ) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.imageResource = imageResource;
    }
}