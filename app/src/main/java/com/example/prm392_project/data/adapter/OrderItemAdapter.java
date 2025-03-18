package com.example.prm392_project.data.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.dto.OrderItemDTO;
import com.example.prm392_project.data.view_holder.OrderItemViewHolder;
import com.example.prm392_project.databinding.ItemOrderItemBinding;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {

    List<OrderItemDTO> listOrderItems;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<OrderItemDTO> listOrderItems) {
        this.listOrderItems = listOrderItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(ItemOrderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItemDTO orderItem = listOrderItems.get(position);
        if (orderItem == null) {
            return;
        }
        holder.bindData(orderItem);
    }

    @Override
    public int getItemCount() {
        return listOrderItems == null ? 0 : listOrderItems.size();
    }
}
