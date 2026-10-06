package com.example.e_commerce_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Profile");
        }

        Button editProfileButton = findViewById(R.id.editProfileButton);
        LinearLayout allOrdersButton = findViewById(R.id.allOrdersButton);
        LinearLayout addressesButton = findViewById(R.id.addressesButton);
        LinearLayout paymentButton = findViewById(R.id.paymentButton);
        LinearLayout couponsButton = findViewById(R.id.couponsButton);
        LinearLayout helpButton = findViewById(R.id.helpButton);
        LinearLayout logoutButton = findViewById(R.id.logoutButton);

        editProfileButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Edit Profile")
                    .setMessage("Profile editing will be added soon.")
                    .setPositiveButton("OK", null)
                    .show();
        });

        allOrdersButton.setOnClickListener(v ->
                Toast.makeText(this, "My Orders", Toast.LENGTH_SHORT).show());

        addressesButton.setOnClickListener(v ->
                Toast.makeText(this, "Saved Addresses", Toast.LENGTH_SHORT).show());

        paymentButton.setOnClickListener(v ->
                Toast.makeText(this, "Payment Methods", Toast.LENGTH_SHORT).show());

        couponsButton.setOnClickListener(v ->
                Toast.makeText(this, "My Coupons", Toast.LENGTH_SHORT).show());

        helpButton.setOnClickListener(v ->
                Toast.makeText(this, "Help & Support", Toast.LENGTH_SHORT).show());

        logoutButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Do you want to log out?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Log Out", (dialog, which) -> {
                        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });
    }
}