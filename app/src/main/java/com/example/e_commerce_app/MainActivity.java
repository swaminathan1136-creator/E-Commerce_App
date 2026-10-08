package com.example.e_commerce_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.e_commerce_app.fragment.FavoritesFragment;
import com.example.e_commerce_app.fragment.HomeFragment;
import com.example.e_commerce_app.fragment.OffersFragment;
import com.example.e_commerce_app.fragment.OrdersFragment;
import com.example.e_commerce_app.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Load HomeFragment by default
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();

            if (itemId == R.id.navHome) {
                selectedFragment = new HomeFragment();
            }
            else if (itemId == R.id.navOrders) {
                selectedFragment = new OrdersFragment();
            }
            else if (itemId == R.id.navFavorites) {
                selectedFragment = new FavoritesFragment();
            }
            else if (itemId == R.id.navOffers) {
                selectedFragment = new OffersFragment();
            }
            else if (itemId == R.id.navProfile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }

            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}