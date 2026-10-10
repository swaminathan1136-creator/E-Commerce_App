package com.example.e_commerce_app;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private final List<CartItem> cartItems = new ArrayList<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(String name, String price, int imageResource) {
        for (CartItem item : cartItems) {
            if (item.name.equals(name)) {
                item.quantity++;
                return;
            }
        }
        cartItems.add(new CartItem(name, price, imageResource, 1));
    }

    public void removeFromCart(String name) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).name.equals(name)) {
                cartItems.remove(i);
                return;
            }
        }
    }

    public void updateQuantity(String name, int quantity) {
        for (CartItem item : cartItems) {
            if (item.name.equals(name)) {
                item.quantity = quantity;
                if (quantity <= 0) {
                    removeFromCart(name);
                }
                return;
            }
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalItems() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.quantity;
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}