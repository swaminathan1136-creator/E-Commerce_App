package com.example.e_commerce_app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        RecyclerView recyclerView = findViewById(R.id.ordersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<OrderItem> orderList = new ArrayList<>();
        orderList.add(new OrderItem("FD1042", "Margherita Pizza, Garlic Bread", "08 Oct 2026", "₹548", "Delivered"));
        orderList.add(new OrderItem("FD1038", "Veg Biryani, Raita", "05 Oct 2026", "₹299", "Delivered"));
        orderList.add(new OrderItem("FD1021", "Cheese Burger, Fries", "01 Oct 2026", "₹349", "Delivered"));
        orderList.add(new OrderItem("FD1015", "Paneer Tikka, Butter Naan", "28 Sep 2026", "₹420", "Delivered"));
        orderList.add(new OrderItem("FD1009", "Farmhouse Pizza", "22 Sep 2026", "₹379", "Delivered"));

        OrderAdapter adapter = new OrderAdapter(orderList, order -> {
            Toast.makeText(this, "Reordering: " + order.orderId, Toast.LENGTH_SHORT).show();
            // Later you can add items to cart here
        });

        recyclerView.setAdapter(adapter);
    }
}