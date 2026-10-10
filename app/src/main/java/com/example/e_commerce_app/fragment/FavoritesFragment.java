package com.example.e_commerce_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_commerce_app.FavoriteItem;
import com.example.e_commerce_app.FavoritesManager;
import com.example.e_commerce_app.R;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private LinearLayout favoritesContainer;
    private TextView emptyText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Toolbar
        View locationContainer = view.findViewById(R.id.locationContainer);
        TextView toolbarTitle = view.findViewById(R.id.toolbarTitle);
        View cartContainer = view.findViewById(R.id.cartContainer);

        if (locationContainer != null) locationContainer.setVisibility(View.GONE);
        if (cartContainer != null) cartContainer.setVisibility(View.GONE);
        if (toolbarTitle != null) {
            toolbarTitle.setVisibility(View.VISIBLE);
            toolbarTitle.setText("Favorites");
        }

        favoritesContainer = view.findViewById(R.id.favoritesContainer);
        emptyText = view.findViewById(R.id.emptyFavoritesText);

        loadFavorites();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        if (favoritesContainer == null) return;

        favoritesContainer.removeAllViews();
        List<FavoriteItem> favorites = FavoritesManager.getInstance().getFavoriteItems();

        if (favorites.isEmpty()) {
            if (emptyText != null) {
                emptyText.setVisibility(View.VISIBLE);
            }
            return;
        }

        if (emptyText != null) {
            emptyText.setVisibility(View.GONE);
        }

        for (FavoriteItem item : favorites) {
            View itemView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_favorite, favoritesContainer, false);

            ImageView foodImage = itemView.findViewById(R.id.favFoodImage);
            TextView foodName = itemView.findViewById(R.id.favFoodName);
            TextView restaurant = itemView.findViewById(R.id.favRestaurant);
            TextView price = itemView.findViewById(R.id.favPrice);
            ImageButton removeButton = itemView.findViewById(R.id.removeFavoriteButton);

            foodImage.setImageResource(item.imageResource);
            foodName.setText(item.name);
            restaurant.setText(item.restaurant + " • ★ " + item.rating);
            price.setText(item.price);

            removeButton.setOnClickListener(v -> {
                FavoritesManager.getInstance().removeFromFavorites(item.name);
                Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                loadFavorites();
            });

            favoritesContainer.addView(itemView);
        }
    }
}