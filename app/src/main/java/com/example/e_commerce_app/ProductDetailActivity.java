package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Details");
        }

        ImageView productImage = findViewById(R.id.productImage);
        TextView productName = findViewById(R.id.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        TextView productDescription = findViewById(R.id.productDescription);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        String name = getIntent().getStringExtra("productName");
        String price = getIntent().getStringExtra("productPrice");
        String description = getIntent().getStringExtra("productDescription");
        int imageResource = getIntent().getIntExtra("productImage", 0);

        if (name != null) {
            productName.setText(name);
            // Optional: also set toolbar title to product name
            if (toolbarTitle != null) {
                toolbarTitle.setText(name);
            }
        }
        if (price != null) productPrice.setText(price);
        if (description != null) productDescription.setText(description);
        if (imageResource != 0) productImage.setImageResource(imageResource);

        addToCartButton.setOnClickListener(v ->
                Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show()
        );
    }
}