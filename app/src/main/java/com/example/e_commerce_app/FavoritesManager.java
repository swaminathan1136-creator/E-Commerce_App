package com.example.e_commerce_app;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {

    private static FavoritesManager instance;
    private final List<FavoriteItem> favoriteItems = new ArrayList<>();

    private FavoritesManager() {}

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    public void addToFavorites(String name, String price, String restaurant, float rating, int imageResource) {
        for (FavoriteItem item : favoriteItems) {
            if (item.name.equals(name)) {
                return; // already exists
            }
        }
        favoriteItems.add(new FavoriteItem(name, price, restaurant, rating, imageResource));
    }

    public void removeFromFavorites(String name) {
        for (int i = 0; i < favoriteItems.size(); i++) {
            if (favoriteItems.get(i).name.equals(name)) {
                favoriteItems.remove(i);
                return;
            }
        }
    }

    public boolean isFavorite(String name) {
        for (FavoriteItem item : favoriteItems) {
            if (item.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<FavoriteItem> getFavoriteItems() {
        return favoriteItems;
    }

    public int getTotalFavorites() {
        return favoriteItems.size();
    }
}