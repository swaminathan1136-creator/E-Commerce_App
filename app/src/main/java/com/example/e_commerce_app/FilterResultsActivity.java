package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FilterResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_results);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Filtered Results");
        }

        // Get filters from Intent
        boolean rating4Plus = getIntent().getBooleanExtra("rating4Plus", false);
        boolean fastDelivery = getIntent().getBooleanExtra("fastDelivery", false);
        boolean offers = getIntent().getBooleanExtra("offers", false);
        boolean pureVeg = getIntent().getBooleanExtra("pureVeg", false);
        String sortBy = getIntent().getStringExtra("sortBy");
        if (sortBy == null) sortBy = "Relevance";

        RecyclerView recyclerView = findViewById(R.id.filterRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FoodItem> allItems = getAllFoodItems();
        List<FoodItem> filteredList = filterItems(allItems, rating4Plus, fastDelivery, offers, pureVeg);

        // Simple sorting
        if (sortBy.equals("Rating")) {
            filteredList.sort((a, b) -> Float.compare(b.rating, a.rating));
        } else if (sortBy.equals("Delivery Time")) {
            filteredList.sort((a, b) -> Integer.compare(a.deliveryTime, b.deliveryTime));
        }

        FilterResultsAdapter adapter = new FilterResultsAdapter(filteredList);
        recyclerView.setAdapter(adapter);

        TextView resultCount = findViewById(R.id.resultCount);
        if (resultCount != null) {
            resultCount.setText(filteredList.size() + " items found");
        }
    }

    private List<FoodItem> getAllFoodItems() {
        List<FoodItem> list = new ArrayList<>();

        list.add(new FoodItem("Margherita Pizza", "Pizza Palace", "₹299", 4.7f, 25, true, true, R.drawable.pizza));
        list.add(new FoodItem("Veg Biryani", "Spice Garden", "₹249", 4.5f, 30, true, false, R.drawable.biryani));
        list.add(new FoodItem("Paneer Tikka", "Urban Tadka", "₹279", 4.6f, 28, true, true, R.drawable.urban_tadka));
        list.add(new FoodItem("Farmhouse Pizza", "Crust & Cheese", "₹349", 4.4f, 22, true, true, R.drawable.crust_cheese));
        list.add(new FoodItem("Masala Dosa", "Food Studio", "₹149", 4.2f, 18, true, false, R.drawable.food_studio));
        list.add(new FoodItem("Veg Thali", "Masala Junction", "₹199", 4.1f, 25, true, false, R.drawable.masala_junction));
        list.add(new FoodItem("Chocolate Shake", "Cafe Aroma", "₹129", 4.0f, 15, true, true, R.drawable.cafe_aroma));
        list.add(new FoodItem("Green Salad Bowl", "Green Bowl", "₹179", 4.3f, 20, true, true, R.drawable.green_bowl));
        list.add(new FoodItem("Chilli Chicken", "Spicy Street", "₹249", 4.2f, 22, false, true, R.drawable.spicy_street));
        list.add(new FoodItem("Veg Manchurian", "Flavour Hub", "₹199", 4.4f, 24, true, false, R.drawable.flavour_hub));
        list.add(new FoodItem("Pepperoni Pizza", "Pizza Palace", "₹379", 4.6f, 26, false, true, R.drawable.pizza));

        return list;
    }

    private List<FoodItem> filterItems(List<FoodItem> allItems, boolean rating4Plus,
                                       boolean fastDelivery, boolean offers, boolean pureVeg) {
        List<FoodItem> result = new ArrayList<>();

        for (FoodItem item : allItems) {
            if (rating4Plus && item.rating < 4.0f) continue;
            if (fastDelivery && item.deliveryTime > 25) continue;
            if (offers && !item.hasOffer) continue;
            if (pureVeg && !item.isPureVeg) continue;

            result.add(item);
        }
        return result;
    }
}