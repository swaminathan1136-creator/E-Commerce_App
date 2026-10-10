package com.example.e_commerce_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartItemsContainer;
    private TextView itemTotalText;
    private TextView totalText;
    private final int deliveryFee = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("My Cart");
        }

        cartItemsContainer = findViewById(R.id.cartItemsContainer);
        itemTotalText = findViewById(R.id.itemTotalText);
        totalText = findViewById(R.id.totalText);

        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        if (placeOrderButton != null) {
            placeOrderButton.setOnClickListener(v -> {
                if (CartManager.getInstance().getCartItems().isEmpty()) {
                    Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    CartManager.getInstance().clearCart();
                    finish();
                }
            });
        }

        loadCartItems();
    }

    private void loadCartItems() {
        cartItemsContainer.removeAllViews();
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        if (cartItems.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("Your cart is empty");
            emptyText.setTextSize(16);
            emptyText.setTextColor(0xFF888888);
            emptyText.setPadding(0, 40, 0, 40);
            cartItemsContainer.addView(emptyText);
            updateBill(0);
            return;
        }

        int itemTotal = 0;

        for (CartItem item : cartItems) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_cart, cartItemsContainer, false);

            ImageView foodImage = itemView.findViewById(R.id.cartFoodImage);
            TextView foodName = itemView.findViewById(R.id.cartFoodName);
            TextView foodPrice = itemView.findViewById(R.id.cartFoodPrice);
            TextView quantityText = itemView.findViewById(R.id.cartQuantityText);
            TextView minusButton = itemView.findViewById(R.id.cartMinusButton);
            TextView plusButton = itemView.findViewById(R.id.cartPlusButton);

            foodImage.setImageResource(item.imageResource);
            foodName.setText(item.name);
            foodPrice.setText(item.price);
            quantityText.setText(String.valueOf(item.quantity));

            // Calculate price
            int priceValue = Integer.parseInt(item.price.replace("₹", "").trim());
            itemTotal += priceValue * item.quantity;

            plusButton.setOnClickListener(v -> {
                CartManager.getInstance().updateQuantity(item.name, item.quantity + 1);
                loadCartItems();
            });

            minusButton.setOnClickListener(v -> {
                if (item.quantity > 1) {
                    CartManager.getInstance().updateQuantity(item.name, item.quantity - 1);
                } else {
                    CartManager.getInstance().removeFromCart(item.name);
                }
                loadCartItems();
            });

            cartItemsContainer.addView(itemView);
        }

        updateBill(itemTotal);
    }

    private void updateBill(int itemTotal) {
        if (itemTotalText != null) {
            itemTotalText.setText("₹" + itemTotal);
        }
        if (totalText != null) {
            totalText.setText("₹" + (itemTotal + deliveryFee));
        }
    }
}