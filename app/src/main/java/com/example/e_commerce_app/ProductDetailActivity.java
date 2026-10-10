package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private boolean isFavorite = false;
    private String productName;
    private String productPrice;
    private int productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        ImageView productImageView = findViewById(R.id.productImage);
        TextView productNameView = findViewById(R.id.productName);
        TextView productPriceView = findViewById(R.id.productPrice);
        TextView productDescription = findViewById(R.id.productDescription);
        Button addToCartButton = findViewById(R.id.addToCartButton);
        ImageButton favoriteButton = findViewById(R.id.favoriteButton);

        productName = getIntent().getStringExtra("productName");
        productPrice = getIntent().getStringExtra("productPrice");
        String description = getIntent().getStringExtra("productDescription");
        productImage = getIntent().getIntExtra("productImage", 0);

        if (productName != null) {
            productNameView.setText(productName);
            if (toolbarTitle != null) {
                toolbarTitle.setText(productName);
            }
        }
        if (productPrice != null) productPriceView.setText(productPrice);
        if (description != null) productDescription.setText(description);
        if (productImage != 0) productImageView.setImageResource(productImage);

        // Check if already favorite
        isFavorite = FavoritesManager.getInstance().isFavorite(productName);
        updateFavoriteIcon(favoriteButton);

        // Add to Cart
        addToCartButton.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(productName, productPrice, productImage);
            Toast.makeText(this, productName + " added to cart", Toast.LENGTH_SHORT).show();
        });

        // Favorite Button
        if (favoriteButton != null) {
            favoriteButton.setOnClickListener(v -> {
                if (isFavorite) {
                    FavoritesManager.getInstance().removeFromFavorites(productName);
                    isFavorite = false;
                    Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    FavoritesManager.getInstance().addToFavorites(
                            productName,
                            productPrice,
                            "Foodie Restaurant",
                            4.5f,
                            productImage
                    );
                    isFavorite = true;
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                }
                updateFavoriteIcon(favoriteButton);
            });
        }
    }

    private void updateFavoriteIcon(ImageButton favoriteButton) {
        if (favoriteButton == null) return;
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
            favoriteButton.setColorFilter(0xFFFF6B00);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
            favoriteButton.setColorFilter(0xFFBDBDBD);
        }
    }
}