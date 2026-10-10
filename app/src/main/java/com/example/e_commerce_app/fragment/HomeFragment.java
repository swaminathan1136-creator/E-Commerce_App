package com.example.e_commerce_app.fragment;

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

import com.example.e_commerce_app.CartActivity;
import com.example.e_commerce_app.CartManager;
import com.example.e_commerce_app.CategoryActivity;
import com.example.e_commerce_app.FilterResultsActivity;
import com.example.e_commerce_app.ProductDetailActivity;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.RestaurantsActivity;
import com.example.e_commerce_app.SearchActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {

    private TextView cartBadge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ==================== TOOLBAR SETUP (Home) ====================
        View locationContainer = view.findViewById(R.id.locationContainer);
        TextView toolbarTitle = view.findViewById(R.id.toolbarTitle);
        TextView deliveryAddress = view.findViewById(R.id.deliveryAddress);
        View cartContainer = view.findViewById(R.id.cartContainer);
        cartBadge = view.findViewById(R.id.cartBadge);

        if (locationContainer != null) locationContainer.setVisibility(View.VISIBLE);
        if (cartContainer != null) cartContainer.setVisibility(View.VISIBLE);
        if (toolbarTitle != null) toolbarTitle.setVisibility(View.GONE);

        updateCartBadge();

        if (locationContainer != null) {
            locationContainer.setOnClickListener(v -> showLocationBottomSheet(deliveryAddress));
        }

        if (cartContainer != null) {
            cartContainer.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), CartActivity.class))
            );
        }

        // ==================== OTHER VIEWS ====================
        Button orderNowButton = view.findViewById(R.id.orderNowButton);
        Button restaurantsButton = view.findViewById(R.id.restaurantsButton);
        FrameLayout filterButton = view.findViewById(R.id.filterButton);
        EditText searchBar = view.findViewById(R.id.searchBar);

        LinearLayout pizzaCategory = view.findViewById(R.id.womenButton);
        LinearLayout burgersCategory = view.findViewById(R.id.menButton);
        LinearLayout biryaniCategory = view.findViewById(R.id.kidsButton);

        ImageView product1 = view.findViewById(R.id.product1);
        ImageView product2 = view.findViewById(R.id.product2);

        // ==================== SEE ALL BUTTONS ====================
        TextView seeAllCategories = view.findViewById(R.id.seeAllCategories);
        TextView seeAllPicks = view.findViewById(R.id.seeAllPicks);
        TextView seeAllRestaurants = view.findViewById(R.id.seeAllRestaurants);
        TextView seeAllBestSellers = view.findViewById(R.id.seeAllBestSellers);

        if (seeAllCategories != null) {
            seeAllCategories.setOnClickListener(v -> openCategory("All"));
        }
        if (seeAllPicks != null) {
            seeAllPicks.setOnClickListener(v -> openCategory("Picks For You"));
        }
        if (seeAllRestaurants != null) {
            seeAllRestaurants.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), RestaurantsActivity.class))
            );
        }
        if (seeAllBestSellers != null) {
            seeAllBestSellers.setOnClickListener(v -> openCategory("Best Sellers"));
        }

        // ==================== SEARCH ====================
        if (searchBar != null) {
            searchBar.setFocusable(false);
            searchBar.setClickable(true);
            searchBar.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), SearchActivity.class))
            );
        }

        if (orderNowButton != null) {
            orderNowButton.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), RestaurantsActivity.class))
            );
        }

        if (restaurantsButton != null) {
            restaurantsButton.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), RestaurantsActivity.class))
            );
        }

        if (filterButton != null) {
            filterButton.setOnClickListener(v -> showFilterBottomSheet());
        }

        if (pizzaCategory != null) pizzaCategory.setOnClickListener(v -> openCategory("Pizza"));
        if (burgersCategory != null) burgersCategory.setOnClickListener(v -> openCategory("Burgers"));
        if (biryaniCategory != null) biryaniCategory.setOnClickListener(v -> openCategory("Biryani"));

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

    @Override
    public void onResume() {
        super.onResume();
        updateCartBadge();
    }

    private void updateCartBadge() {
        if (cartBadge != null) {
            int count = CartManager.getInstance().getTotalItems();
            if (count > 0) {
                cartBadge.setVisibility(View.VISIBLE);
                cartBadge.setText(String.valueOf(count));
            } else {
                cartBadge.setVisibility(View.GONE);
            }
        }
    }

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

    private void showFilterBottomSheet() {
        try {
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_filter, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();

            final TextView chipRelevance = bottomSheetView.findViewById(R.id.chipRelevance);
            final TextView chipRatingHigh = bottomSheetView.findViewById(R.id.chipRatingHigh);
            final TextView chipRatingLow = bottomSheetView.findViewById(R.id.chipRatingLow);
            final TextView chipRecent = bottomSheetView.findViewById(R.id.chipRecent);
            final TextView chipDeliveryTime = bottomSheetView.findViewById(R.id.chipDeliveryTime);

            final TextView chipRating4 = bottomSheetView.findViewById(R.id.chipRating4);
            final TextView chipFastDelivery = bottomSheetView.findViewById(R.id.chipFastDelivery);
            final TextView chipOffers = bottomSheetView.findViewById(R.id.chipOffers);
            final TextView chipPureVeg = bottomSheetView.findViewById(R.id.chipPureVeg);

            Button btnClearAll = bottomSheetView.findViewById(R.id.btnClearAll);
            Button btnApply = bottomSheetView.findViewById(R.id.btnApply);

            if (btnClearAll == null || btnApply == null) {
                Toast.makeText(requireContext(), "Filter layout error", Toast.LENGTH_LONG).show();
                return;
            }

            View.OnClickListener sortListener = v -> {
                TextView clicked = (TextView) v;
                if (clicked.getCurrentTextColor() == 0xFFFF6B00) {
                    clicked.setTextColor(0xFF333333);
                    return;
                }
                if (chipRelevance != null) chipRelevance.setTextColor(0xFF333333);
                if (chipRatingHigh != null) chipRatingHigh.setTextColor(0xFF333333);
                if (chipRatingLow != null) chipRatingLow.setTextColor(0xFF333333);
                if (chipRecent != null) chipRecent.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);
                clicked.setTextColor(0xFFFF6B00);
            };

            if (chipRelevance != null) chipRelevance.setOnClickListener(sortListener);
            if (chipRatingHigh != null) chipRatingHigh.setOnClickListener(sortListener);
            if (chipRatingLow != null) chipRatingLow.setOnClickListener(sortListener);
            if (chipRecent != null) chipRecent.setOnClickListener(sortListener);
            if (chipDeliveryTime != null) chipDeliveryTime.setOnClickListener(sortListener);

            if (chipRating4 != null) {
                chipRating4.setOnClickListener(v -> {
                    chipRating4.setTextColor(chipRating4.getCurrentTextColor() == 0xFFFF6B00 ? 0xFF333333 : 0xFFFF6B00);
                });
            }
            if (chipFastDelivery != null) {
                chipFastDelivery.setOnClickListener(v -> {
                    chipFastDelivery.setTextColor(chipFastDelivery.getCurrentTextColor() == 0xFFFF6B00 ? 0xFF333333 : 0xFFFF6B00);
                });
            }
            if (chipOffers != null) {
                chipOffers.setOnClickListener(v -> {
                    chipOffers.setTextColor(chipOffers.getCurrentTextColor() == 0xFFFF6B00 ? 0xFF333333 : 0xFFFF6B00);
                });
            }
            if (chipPureVeg != null) {
                chipPureVeg.setOnClickListener(v -> {
                    chipPureVeg.setTextColor(chipPureVeg.getCurrentTextColor() == 0xFFFF6B00 ? 0xFF333333 : 0xFFFF6B00);
                });
            }

            btnClearAll.setOnClickListener(v -> {
                if (chipRelevance != null) chipRelevance.setTextColor(0xFF333333);
                if (chipRatingHigh != null) chipRatingHigh.setTextColor(0xFF333333);
                if (chipRatingLow != null) chipRatingLow.setTextColor(0xFF333333);
                if (chipRecent != null) chipRecent.setTextColor(0xFF333333);
                if (chipDeliveryTime != null) chipDeliveryTime.setTextColor(0xFF333333);
                if (chipRating4 != null) chipRating4.setTextColor(0xFF333333);
                if (chipFastDelivery != null) chipFastDelivery.setTextColor(0xFF333333);
                if (chipOffers != null) chipOffers.setTextColor(0xFF333333);
                if (chipPureVeg != null) chipPureVeg.setTextColor(0xFF333333);
                Toast.makeText(requireContext(), "Filters cleared", Toast.LENGTH_SHORT).show();
            });

            btnApply.setOnClickListener(v -> {
                boolean rating4Plus = chipRating4 != null && chipRating4.getCurrentTextColor() == 0xFFFF6B00;
                boolean fastDelivery = chipFastDelivery != null && chipFastDelivery.getCurrentTextColor() == 0xFFFF6B00;
                boolean offers = chipOffers != null && chipOffers.getCurrentTextColor() == 0xFFFF6B00;
                boolean pureVeg = chipPureVeg != null && chipPureVeg.getCurrentTextColor() == 0xFFFF6B00;

                String sortBy = "Relevance";
                if (chipRatingHigh != null && chipRatingHigh.getCurrentTextColor() == 0xFFFF6B00) {
                    sortBy = "Rating High to Low";
                } else if (chipRatingLow != null && chipRatingLow.getCurrentTextColor() == 0xFFFF6B00) {
                    sortBy = "Rating Low to High";
                } else if (chipRecent != null && chipRecent.getCurrentTextColor() == 0xFFFF6B00) {
                    sortBy = "Recent";
                } else if (chipDeliveryTime != null && chipDeliveryTime.getCurrentTextColor() == 0xFFFF6B00) {
                    sortBy = "Delivery Time";
                }

                Intent intent = new Intent(requireContext(), FilterResultsActivity.class);
                intent.putExtra("rating4Plus", rating4Plus);
                intent.putExtra("fastDelivery", fastDelivery);
                intent.putExtra("offers", offers);
                intent.putExtra("pureVeg", pureVeg);
                intent.putExtra("sortBy", sortBy);
                startActivity(intent);
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