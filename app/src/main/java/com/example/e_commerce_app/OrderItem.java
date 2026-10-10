package com.example.e_commerce_app;

public class OrderItem {
    public String orderId;
    public String items;
    public String date;
    public String total;
    public String status;

    public OrderItem(String orderId, String items, String date, String total, String status) {
        this.orderId = orderId;
        this.items = items;
        this.date = date;
        this.total = total;
        this.status = status;
    }
}