
package com.example.e_commerce_app.Item;

public class RestaurantItem {

    private String name;
    private String rating;
    private String deliveryTime;
    private int imageRes;
    private String offerText;

    public RestaurantItem(
            String name,
            String rating,
            String deliveryTime,
            int imageRes,
            String offerText) {

        this.name = name;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.imageRes = imageRes;
        this.offerText = offerText;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getOfferText() {
        return offerText;
    }
}