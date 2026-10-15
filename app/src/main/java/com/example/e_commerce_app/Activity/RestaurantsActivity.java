
package com.example.e_commerce_app.Activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_app.ApiClient;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.Adapter.RestaurantAdapter;
import com.example.e_commerce_app.Item.RestaurantItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<RestaurantItem> restaurantList = new ArrayList<>();

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

        // Restaurant RecyclerView
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(adapter);

        // Display static restaurant data for now
        loadStaticRestaurants();

        // Enable this later when you want to fetch data from Flask:
        // fetchRestaurants();
    }

    // ================= STATIC RESTAURANT DATA =================

    private void loadStaticRestaurants() {

        restaurantList.clear();

        restaurantList.add(new RestaurantItem(
                "Cafe Aroma",
                "4.5",
                "20 min",
                R.drawable.cafe_aroma,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Curry House",
                "4.3",
                "30 min",
                R.drawable.curry_house,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Food Republic",
                "4.4",
                "25 min",
                R.drawable.food_republic,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Food Studio",
                "4.2",
                "25 min",
                R.drawable.food_studio,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Flavour Hub",
                "4.6",
                "20 min",
                R.drawable.flavour_hub,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Green Bowl",
                "4.5",
                "20 min",
                R.drawable.green_bowl,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Masala Junction",
                "4.4",
                "30 min",
                R.drawable.masala_junction,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Spice Garden",
                "4.7",
                "35 min",
                R.drawable.spice_garden,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Spicy Street",
                "4.2",
                "25 min",
                R.drawable.spicy_street,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Tandoori Tales",
                "4.5",
                "30 min",
                R.drawable.tandoori_tales,
                ""
        ));

        restaurantList.add(new RestaurantItem(
                "Urban Tadka",
                "4.3",
                "25 min",
                R.drawable.urban_tadka,
                ""
        ));

        // Refresh the RecyclerView
        adapter.notifyDataSetChanged();
    }

    // ================= BACKEND API =================
    // Keep this method for later use when connecting to Flask.

    private void fetchRestaurants() {

        String baseUrl = ApiClient.BASE_URL;

        String url = baseUrl.endsWith("/")
                ? baseUrl + "get_restaurants"
                : baseUrl + "/get_restaurants";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,

                response -> {
                    try {
                        restaurantList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            String name = obj.getString("name");
                            String rating = obj.getString("rating");

                            String deliveryTime =
                                    obj.getString("delivery_time") + " min";

                            String offerText = obj.isNull("offer_text")
                                    ? ""
                                    : obj.getString("offer_text");

                            // Existing local image used as a fallback
                            int imageRes = R.drawable.pizza;

                            restaurantList.add(
                                    new RestaurantItem(
                                            name,
                                            rating,
                                            deliveryTime,
                                            imageRes,
                                            offerText
                                    )
                            );
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();

                        Toast.makeText(
                                RestaurantsActivity.this,
                                "Parsing error",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                },

                error -> Toast.makeText(
                        RestaurantsActivity.this,
                        "Error: " + error.toString(),
                        Toast.LENGTH_LONG
                ).show()
        );

        queue.add(request);
    }
}
