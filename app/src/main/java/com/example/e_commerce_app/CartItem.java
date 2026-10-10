package com.example.e_commerce_app;

public class CartItem {
    public String name;
    public String price;
    public int imageResource;
    public int quantity;

    public CartItem(String name, String price, int imageResource, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.quantity = quantity;
    }
}