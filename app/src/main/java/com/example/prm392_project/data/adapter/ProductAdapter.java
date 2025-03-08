package com.example.prm392_project.data.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.view_holder.ProductViewHolder;
import com.example.prm392_project.databinding.ItemProductHomeBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Products> products;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onClick(Products product);
    }

    public ProductAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setData(List<Products> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(ItemProductHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products product = products.get(position);
        if (product == null) {
            return;
        }
        holder.bindProduct(product);
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }
}
