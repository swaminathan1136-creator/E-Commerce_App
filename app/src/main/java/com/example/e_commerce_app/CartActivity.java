package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private int pizzaQuantity = 1;
    private int biryaniQuantity = 1;

    private final int pizzaPrice = 299;
    private final int biryaniPrice = 249;
    private final int deliveryFee = 30;

    private TextView pizzaQuantityText;
    private TextView biryaniQuantityText;
    private TextView itemTotalText;
    private TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("My Cart");
        }

        // Place Order Button
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        if (placeOrderButton != null) {
            placeOrderButton.setOnClickListener(v ->
                    Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
            );
        }

        // Quantity Views
        TextView pizzaMinusButton = findViewById(R.id.pizzaMinusButton);
        TextView pizzaPlusButton = findViewById(R.id.pizzaPlusButton);
        pizzaQuantityText = findViewById(R.id.pizzaQuantityText);

        TextView biryaniMinusButton = findViewById(R.id.biryaniMinusButton);
        TextView biryaniPlusButton = findViewById(R.id.biryaniPlusButton);
        biryaniQuantityText = findViewById(R.id.biryaniQuantityText);

        itemTotalText = findViewById(R.id.itemTotalText);
        totalText = findViewById(R.id.totalText);

        // Pizza Quantity
        pizzaPlusButton.setOnClickListener(v -> {
            pizzaQuantity++;
            updateCart();
        });

        pizzaMinusButton.setOnClickListener(v -> {
            if (pizzaQuantity > 1) {
                pizzaQuantity--;
                updateCart();
            }
        });

        // Biryani Quantity
        biryaniPlusButton.setOnClickListener(v -> {
            biryaniQuantity++;
            updateCart();
        });

        biryaniMinusButton.setOnClickListener(v -> {
            if (biryaniQuantity > 1) {
                biryaniQuantity--;
                updateCart();
            }
        });

        updateCart();
    }

    private void updateCart() {
        pizzaQuantityText.setText(String.valueOf(pizzaQuantity));
        biryaniQuantityText.setText(String.valueOf(biryaniQuantity));

        int itemTotal = (pizzaPrice * pizzaQuantity) + (biryaniPrice * biryaniQuantity);
        int total = itemTotal + deliveryFee;

        itemTotalText.setText("₹" + itemTotal);
        totalText.setText("₹" + total);
    }
}