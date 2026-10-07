package com.example.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button orderNowButton = view.findViewById(R.id.orderNowButton);
        Button restaurantsButton = view.findViewById(R.id.restaurantsButton);
        FrameLayout filterButton = view.findViewById(R.id.filterButton);
        EditText searchBar = view.findViewById(R.id.searchBar);

        LinearLayout pizzaCategory = view.findViewById(R.id.womenButton);
        LinearLayout burgersCategory = view.findViewById(R.id.menButton);
        LinearLayout biryaniCategory = view.findViewById(R.id.kidsButton);

        ImageView product1 = view.findViewById(R.id.product1);
        ImageView product2 = view.findViewById(R.id.product2);

        View cartClickArea = view.findViewById(R.id.cartContainer);
        if (cartClickArea == null) {
            cartClickArea = view.findViewById(R.id.cartIcon);
        }

        // ==================== LOCATION CLICK ====================
        View locationContainer = view.findViewById(R.id.locationContainer);
        TextView deliveryAddress = view.findViewById(R.id.deliveryAddress);

        if (locationContainer != null) {
            locationContainer.setOnClickListener(v -> showLocationBottomSheet(deliveryAddress));
        }

        // Search Bar
        if (searchBar != null) {
            searchBar.setFocusable(false);
            searchBar.setClickable(true);
            searchBar.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), SearchActivity.class))
            );
        }

        // Order Now
        if (orderNowButton != null) {
            orderNowButton.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), RestaurantsActivity.class))
            );
        }

        // View All Restaurants
        if (restaurantsButton != null) {
            restaurantsButton.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), RestaurantsActivity.class))
            );
        }

        // Filter Button
        if (filterButton != null) {
            filterButton.setOnClickListener(v -> showFilterBottomSheet());
        }

        // Cart
        if (cartClickArea != null) {
            cartClickArea.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), CartActivity.class))
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
    }

    // ==================== LOCATION BOTTOM SHEET ====================
    private void showLocationBottomSheet(TextView deliveryAddress) {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_location, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

        View optionCurrent = bottomSheetView.findViewById(R.id.optionCurrentLocation);
        View optionHome = bottomSheetView.findViewById(R.id.optionHome);
        View optionWork = bottomSheetView.findViewById(R.id.optionWork);
        View optionOther = bottomSheetView.findViewById(R.id.optionOther);

        if (optionCurrent != null) {
            optionCurrent.setOnClickListener(v -> {
                if (deliveryAddress != null) deliveryAddress.setText("Current Location  ▾");
                Toast.makeText(requireContext(), "Using current location", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }

        if (optionHome != null) {
            optionHome.setOnClickListener(v -> {
                if (deliveryAddress != null) deliveryAddress.setText("Home  ▾");
                Toast.makeText(requireContext(), "Location set to Home", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }

        if (optionWork != null) {
            optionWork.setOnClickListener(v -> {
                if (deliveryAddress != null) deliveryAddress.setText("Work  ▾");
                Toast.makeText(requireContext(), "Location set to Work", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }

        if (optionOther != null) {
            optionOther.setOnClickListener(v -> {
                if (deliveryAddress != null) deliveryAddress.setText("JetHat Butani Infra  ▾");
                Toast.makeText(requireContext(), "Location updated", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            });
        }
    }

    // ==================== FILTER BOTTOM SHEET ====================
    private void showFilterBottomSheet() {
        try {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_filter, null);

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
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
                Toast.makeText(requireContext(), "Filter layout error", Toast.LENGTH_LONG).show();
                return;
            }

            View.OnClickListener sortClickListener = v -> {
                if (chipRelevance != null) chipRelevance.setTextColor(0xFF333333);
                if (chipRating != null) chipRating.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);
                ((TextView) v).setTextColor(0xFFFF6B00);
            };

            if (chipRelevance != null) chipRelevance.setOnClickListener(sortClickListener);
            if (chipRating != null) chipRating.setOnClickListener(sortClickListener);
            if (chipDeliveryTime != null) chipDeliveryTime.setOnClickListener(sortClickListener);

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

            btnClearAll.setOnClickListener(v -> {
                if (chipRelevance != null) chipRelevance.setTextColor(0xFFFF6B00);
                if (chipRating != null) chipRating.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);

                if (chipRating4 != null) chipRating4.setTextColor(0xFF333333);
                if (chipFastDelivery != null) chipFastDelivery.setTextColor(0xFF333333);
                if (chipOffers != null) chipOffers.setTextColor(0xFF333333);
                if (chipPureVeg != null) chipPureVeg.setTextColor(0xFF333333);

                Toast.makeText(requireContext(), "Filters cleared", Toast.LENGTH_SHORT).show();
            });

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

                Toast.makeText(requireContext(), "Applied: " + selected, Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
            });

        } catch (Exception e) {
            Toast.makeText(requireContext(), "Filter Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void openCategory(String category) {
        Intent intent = new Intent(requireContext(), CategoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    private void openProduct(String name, String price, int imageResource, String description) {
        Intent intent = new Intent(requireContext(), ProductDetailActivity.class);
        intent.putExtra("productName", name);
        intent.putExtra("productPrice", price);
        intent.putExtra("productImage", imageResource);
        intent.putExtra("productDescription", description);
        startActivity(intent);
    }
}