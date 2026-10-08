package com.example.e_commerce_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilterResultsAdapter extends RecyclerView.Adapter<FilterResultsAdapter.ViewHolder> {

    private final List<FoodItem> itemList;

    public FilterResultsAdapter(List<FoodItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = itemList.get(position);

        holder.foodName.setText(item.name);
        holder.restaurantName.setText(item.restaurant);
        holder.price.setText(item.price);
        holder.rating.setText(String.format("★ %.1f", item.rating));
        holder.deliveryTime.setText(item.deliveryTime + " min");
        holder.foodImage.setImageResource(item.imageResource);

        if (item.isPureVeg) {
            holder.vegBadge.setVisibility(View.VISIBLE);
        } else {
            holder.vegBadge.setVisibility(View.GONE);
        }

        if (item.hasOffer) {
            holder.offerBadge.setVisibility(View.VISIBLE);
        } else {
            holder.offerBadge.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodName, restaurantName, price, rating, deliveryTime;
        TextView vegBadge, offerBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
            vegBadge = itemView.findViewById(R.id.vegBadge);
            offerBadge = itemView.findViewById(R.id.offerBadge);
        }
    }
}