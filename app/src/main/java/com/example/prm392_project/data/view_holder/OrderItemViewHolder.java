package com.example.prm392_project.data.view_holder;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.dto.OrderItemDTO;
import com.example.prm392_project.databinding.ItemOrderItemBinding;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    ItemOrderItemBinding binding;

    public OrderItemViewHolder(ItemOrderItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @SuppressLint("DefaultLocale")
    public void bindData(OrderItemDTO orderItem) {
        binding.productName.setText(orderItem.getProductName());
        binding.productImage.setImageResource(orderItem.getProductImage());
        binding.productSize.setText(String.format("%d", orderItem.getSize()));
        binding.productPrice.setText(String.format("%.2f", orderItem.getPrice()));
        binding.quantity.setText(String.format("%s%d", "x", orderItem.getQuantity()));
    }
}
