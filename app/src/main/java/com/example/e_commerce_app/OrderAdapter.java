package com.example.e_commerce_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public interface OnReorderClickListener {
        void onReorder(OrderItem order);
    }

    private final List<OrderItem> orderList;
    private final OnReorderClickListener listener;

    public OrderAdapter(List<OrderItem> orderList, OnReorderClickListener listener) {
        this.orderList = orderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem order = orderList.get(position);

        holder.orderId.setText("Order #" + order.orderId);
        holder.orderItems.setText(order.items);
        holder.orderDate.setText("Ordered on " + order.date);
        holder.orderTotal.setText(order.total);
        holder.orderStatus.setText(order.status);

        holder.reorderButton.setOnClickListener(v -> listener.onReorder(order));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderItems, orderDate, orderTotal, orderStatus;
        Button reorderButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderItems = itemView.findViewById(R.id.orderItems);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderTotal = itemView.findViewById(R.id.orderTotal);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            reorderButton = itemView.findViewById(R.id.reorderButton);
        }
    }
}