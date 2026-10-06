package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Restaurants");
        }

        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RestaurantItem> restaurantList = new ArrayList<>();

        restaurantList.add(new RestaurantItem("Pizza Palace", "Italian • Pizza", "★ 4.6", "20–25 min", R.drawable.pizza));
        restaurantList.add(new RestaurantItem("Spice Garden", "Indian • Biryani", "★ 4.5", "25–30 min", R.drawable.spice_garden));
        restaurantList.add(new RestaurantItem("Urban Tadka", "North Indian", "★ 4.4", "25–35 min", R.drawable.urban_tadka));
        restaurantList.add(new RestaurantItem("Burger Junction", "Fast Food • Burgers", "★ 4.3", "20–25 min", R.drawable.burger));
        restaurantList.add(new RestaurantItem("Curry House", "Indian • Curry", "★ 4.5", "30–35 min", R.drawable.curry_house));
        restaurantList.add(new RestaurantItem("Food Studio", "Multi Cuisine", "★ 4.7", "20–30 min", R.drawable.food_studio));
        restaurantList.add(new RestaurantItem("Masala Junction", "Vegetarian • Indian", "★ 4.4", "25–30 min", R.drawable.masala_junction));
        restaurantList.add(new RestaurantItem("Cafe Aroma", "Cafe • Snacks", "★ 4.2", "15–25 min", R.drawable.cafe_aroma));
        restaurantList.add(new RestaurantItem("Royal Biryani", "Biryani • Indian", "★ 4.6", "30–35 min", R.drawable.biryani));
        restaurantList.add(new RestaurantItem("Tandoori Tales", "Tandoor • Indian", "★ 4.5", "25–35 min", R.drawable.tandoori_tales));
        restaurantList.add(new RestaurantItem("Crust & Cheese", "Pizza • Italian", "★ 4.4", "20–25 min", R.drawable.crust_cheese));
        restaurantList.add(new RestaurantItem("Green Bowl", "Healthy • Vegetarian", "★ 4.3", "20–30 min", R.drawable.green_bowl));
        restaurantList.add(new RestaurantItem("Spicy Street", "Street Food • Indian", "★ 4.2", "15–25 min", R.drawable.spicy_street));
        restaurantList.add(new RestaurantItem("Food Republic", "Multi Cuisine", "★ 4.6", "25–30 min", R.drawable.food_republic));
        restaurantList.add(new RestaurantItem("Flavour Hub", "Indian • Chinese", "★ 4.5", "20–30 min", R.drawable.flavour_hub));

        RestaurantAdapter adapter = new RestaurantAdapter(restaurantList);
        restaurantRecyclerView.setAdapter(adapter);
    }
}