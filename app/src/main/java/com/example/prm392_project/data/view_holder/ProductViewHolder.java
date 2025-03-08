package com.example.prm392_project.data.view_holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.databinding.ItemProductHomeBinding;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private ItemProductHomeBinding binding;

    public ProductViewHolder(ItemProductHomeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindProduct(Products product) {
        binding.txtCategory.setText(product.getName());
        binding.txtPrice.setText(product.getPrice().toString());
        binding.imgShoe.setImageResource(product.getImageResId());
    }

}
