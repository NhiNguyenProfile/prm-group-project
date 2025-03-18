package com.example.prm392_project.data.view_holder;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.databinding.ItemOrderBinding;
import com.example.prm392_project.util.ConvertHelper;

import java.time.format.DateTimeFormatter;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private ItemOrderBinding binding;

    public OrderViewHolder(@NonNull ItemOrderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @SuppressLint("DefaultLocale")
    public void bindData(Orders orders) {
        binding.orderId.setText(orders.getId());
        binding.status.setText(orders.getStatus());
        binding.totalAmount.setText(String.format("%.2f", orders.getTotalAmount()));
        binding.orderDate.setText(ConvertHelper.fromTimestamp(orders.getCreateTime()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public RecyclerView getOrderItemRecycleView() {
        return binding.recyclerOrderItems;
    }
}
