package com.example.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Search");
        }

        EditText searchInput = findViewById(R.id.searchInput);

        // Auto focus the search bar
        if (searchInput != null) {
            searchInput.requestFocus();
        }

        setupRecentSearchClicks();
        setupCategoryClicks();
    }

    private void setupRecentSearchClicks() {
        int[] recentIds = {
                R.id.recentPizza,
                R.id.recentBurgers,
                R.id.recentBiryani,
                R.id.recentDessert
        };

        String[] recentNames = {"Pizza", "Burgers", "Biryani", "Dessert"};

        for (int i = 0; i < recentIds.length; i++) {
            TextView tv = findViewById(recentIds[i]);
            if (tv != null) {
                String name = recentNames[i];
                tv.setOnClickListener(v -> {
                    Toast.makeText(this, "Searching: " + name, Toast.LENGTH_SHORT).show();
                    openCategory(name);
                });
            }
        }
    }

    private void setupCategoryClicks() {
        int[] categoryIds = {
                R.id.catAll,
                R.id.catPizza,
                R.id.catBurgers,
                R.id.catBiryani,
                R.id.catDessert
        };

        String[] categoryNames = {"All", "Pizza", "Burgers", "Biryani", "Dessert"};

        for (int i = 0; i < categoryIds.length; i++) {
            TextView tv = findViewById(categoryIds[i]);
            if (tv != null) {
                String name = categoryNames[i];
                tv.setOnClickListener(v -> {
                    if (name.equals("All")) {
                        startActivity(new Intent(this, RestaurantsActivity.class));
                    } else {
                        openCategory(name);
                    }
                });
            }
        }
    }

    private void openCategory(String category) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}