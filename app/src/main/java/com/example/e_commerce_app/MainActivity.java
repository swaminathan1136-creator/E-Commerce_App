package com.example.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        Button orderNowButton = findViewById(R.id.orderNowButton);
        Button restaurantsButton = findViewById(R.id.restaurantsButton);
        FrameLayout filterButton = findViewById(R.id.filterButton);
        EditText searchBar = findViewById(R.id.searchBar);

        LinearLayout pizzaCategory = findViewById(R.id.womenButton);
        LinearLayout burgersCategory = findViewById(R.id.menButton);
        LinearLayout biryaniCategory = findViewById(R.id.kidsButton);

        ImageView product1 = findViewById(R.id.product1);
        ImageView product2 = findViewById(R.id.product2);

        // Prefer cartContainer if you updated the XML, otherwise cartIcon
        View cartClickArea = findViewById(R.id.cartContainer);
        if (cartClickArea == null) {
            cartClickArea = findViewById(R.id.cartIcon);
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Search Bar → open Search screen
        if (searchBar != null) {
            searchBar.setFocusable(false);
            searchBar.setClickable(true);
            searchBar.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, SearchActivity.class))
            );
        }

        // Order Now
        if (orderNowButton != null) {
            orderNowButton.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, RestaurantsActivity.class))
            );
        }

        // View All Restaurants
        if (restaurantsButton != null) {
            restaurantsButton.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, RestaurantsActivity.class))
            );
        }

        // Filter Button
        if (filterButton != null) {
            filterButton.setOnClickListener(v -> showFilterBottomSheet());
        }

        // Cart
        if (cartClickArea != null) {
            cartClickArea.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, CartActivity.class))
            );
        }

        // Categories
        if (pizzaCategory != null) pizzaCategory.setOnClickListener(v -> openCategory("Pizza"));
        if (burgersCategory != null) burgersCategory.setOnClickListener(v -> openCategory("Burgers"));
        if (biryaniCategory != null) biryaniCategory.setOnClickListener(v -> openCategory("Biryani"));

        // Food cards
        if (product1 != null) {
            product1.setOnClickListener(v -> openProduct(
                    "Margherita Pizza",
                    "₹299",
                    R.drawable.pizza,
                    "Freshly baked pizza topped with tomato sauce, mozzarella cheese and Italian herbs."
            ));
        }

        if (product2 != null) {
            product2.setOnClickListener(v -> openProduct(
                    "Veg Biryani",
                    "₹249",
                    R.drawable.biryani,
                    "Aromatic basmati rice cooked with spices and herbs."
            ));
        }

        // Bottom Navigation
        if (bottomNavigation != null) {
            bottomNavigation.setOnItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.navHome) {
                    return true;
                } else if (id == R.id.navOffers) {
                    startActivity(new Intent(MainActivity.this, OffersActivity.class));
                    return true;
                } else if (id == R.id.navFavorites) {
                    startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                    return true;
                } else if (id == R.id.navOrders) {
                    startActivity(new Intent(MainActivity.this, OrdersActivity.class));
                    return true;
                } else if (id == R.id.navProfile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            });
        }
    }

    private void showFilterBottomSheet() {
        try {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_filter, null);

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();

            TextView chipRelevance = bottomSheetView.findViewById(R.id.chipRelevance);
            TextView chipRating = bottomSheetView.findViewById(R.id.chipRating);
            TextView chipDeliveryTime = bottomSheetView.findViewById(R.id.chipDeliveryTime);

            TextView chipRating4 = bottomSheetView.findViewById(R.id.chipRating4);
            TextView chipFastDelivery = bottomSheetView.findViewById(R.id.chipFastDelivery);
            TextView chipOffers = bottomSheetView.findViewById(R.id.chipOffers);
            TextView chipPureVeg = bottomSheetView.findViewById(R.id.chipPureVeg);

            Button btnClearAll = bottomSheetView.findViewById(R.id.btnClearAll);
            Button btnApply = bottomSheetView.findViewById(R.id.btnApply);

            if (btnClearAll == null || btnApply == null) {
                Toast.makeText(this, "Filter layout error", Toast.LENGTH_LONG).show();
                return;
            }

            // Sort selection
            View.OnClickListener sortClickListener = v -> {
                if (chipRelevance != null) chipRelevance.setTextColor(0xFF333333);
                if (chipRating != null) chipRating.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);
                ((TextView) v).setTextColor(0xFFFF6B00);
            };

            if (chipRelevance != null) chipRelevance.setOnClickListener(sortClickListener);
            if (chipRating != null) chipRating.setOnClickListener(sortClickListener);
            if (chipDeliveryTime != null) chipDeliveryTime.setOnClickListener(sortClickListener);

            // Toggle filters
            View.OnClickListener filterClickListener = v -> {
                TextView tv = (TextView) v;
                if (tv.getCurrentTextColor() == 0xFFFF6B00) {
                    tv.setTextColor(0xFF333333);
                } else {
                    tv.setTextColor(0xFFFF6B00);
                }
            };

            if (chipRating4 != null) chipRating4.setOnClickListener(filterClickListener);
            if (chipFastDelivery != null) chipFastDelivery.setOnClickListener(filterClickListener);
            if (chipOffers != null) chipOffers.setOnClickListener(filterClickListener);
            if (chipPureVeg != null) chipPureVeg.setOnClickListener(filterClickListener);

            // Clear All
            btnClearAll.setOnClickListener(v -> {
                if (chipRelevance != null) chipRelevance.setTextColor(0xFFFF6B00);
                if (chipRating != null) chipRating.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);

                if (chipRating4 != null) chipRating4.setTextColor(0xFF333333);
                if (chipFastDelivery != null) chipFastDelivery.setTextColor(0xFF333333);
                if (chipOffers != null) chipOffers.setTextColor(0xFF333333);
                if (chipPureVeg != null) chipPureVeg.setTextColor(0xFF333333);

                Toast.makeText(this, "Filters cleared", Toast.LENGTH_SHORT).show();
            });

            // Apply
            btnApply.setOnClickListener(v -> {
                StringBuilder selected = new StringBuilder();

                if (chipRating != null && chipRating.getCurrentTextColor() == 0xFFFF6B00) {
                    selected.append("Rating");
                } else if (chipDeliveryTime != null && chipDeliveryTime.getCurrentTextColor() == 0xFFFF6B00) {
                    selected.append("Delivery Time");
                } else {
                    selected.append("Relevance");
                }

                if (chipRating4 != null && chipRating4.getCurrentTextColor() == 0xFFFF6B00)
                    selected.append(", Rating 4.0+");
                if (chipFastDelivery != null && chipFastDelivery.getCurrentTextColor() == 0xFFFF6B00)
                    selected.append(", Fast Delivery");
                if (chipOffers != null && chipOffers.getCurrentTextColor() == 0xFFFF6B00)
                    selected.append(", Offers");
                if (chipPureVeg != null && chipPureVeg.getCurrentTextColor() == 0xFFFF6B00)
                    selected.append(", Pure Veg");

                Toast.makeText(this, "Applied: " + selected, Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
            });

        } catch (Exception e) {
            Toast.makeText(this, "Filter Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void openCategory(String category) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void openProduct(String name, String price, int imageResource, String description) {
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("productName", name);
        intent.putExtra("productPrice", price);
        intent.putExtra("productImage", imageResource);
        intent.putExtra("productDescription", description);
        startActivity(intent);
    }
}