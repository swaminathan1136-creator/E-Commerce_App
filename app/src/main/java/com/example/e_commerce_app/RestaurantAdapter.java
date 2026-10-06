package com.example.e_commerce_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final List<RestaurantItem> restaurantList;

    public RestaurantAdapter(List<RestaurantItem> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantItem item = restaurantList.get(position);

        holder.restaurantName.setText(item.name);
        holder.restaurantCuisine.setText(item.cuisine);
        holder.restaurantRating.setText(item.rating);
        holder.restaurantDeliveryTime.setText(item.deliveryTime);
        holder.restaurantImage.setImageResource(item.imageResource);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImage;
        TextView restaurantName;
        TextView restaurantCuisine;
        TextView restaurantRating;
        TextView restaurantDeliveryTime;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantCuisine = itemView.findViewById(R.id.restaurantCuisine);
            restaurantRating = itemView.findViewById(R.id.restaurantRating);
            restaurantDeliveryTime = itemView.findViewById(R.id.restaurantDeliveryTime);
        }
    }
}