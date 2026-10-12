package com.example.e_commerce_app;

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

        recyclerView = findViewById(R.id.restaurantsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(adapter);

        fetchRestaurants();
    }

    private void fetchRestaurants() {
        String url = ApiClient.BASE_URL + "get_restaurants";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        restaurantList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            String name = obj.getString("name");
                            String rating = obj.getString("rating");
                            String deliveryTime = obj.getString("delivery_time") + " min";
                            String offerText = obj.isNull("offer_text") ? "" : obj.getString("offer_text");

                            // You can later map image from database
                            int imageRes = R.drawable.pizza;

                            restaurantList.add(new RestaurantItem(name, rating, deliveryTime, imageRes, offerText));
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                });

        queue.add(request);
    }
}