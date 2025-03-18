package com.example.prm392_project.data.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.view_holder.OrderViewHolder;
import com.example.prm392_project.databinding.ItemOrderBinding;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private List<Orders> listOrder;
    private OnClickListener onClickListener;
    private int lastExpandedPosition = -1; // Lưu vị trí đang mở


    public interface OnClickListener {
        void onItemClick(Orders order, RecyclerView recycleView);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Orders> listOrder) {
        this.listOrder = listOrder;
        notifyDataSetChanged();
    }

    public OrderAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Orders order = listOrder.get(position);
        if (order == null) {
            return;
        }
        holder.bindData(order);
        boolean isExpanded = position == lastExpandedPosition;
        holder.getOrderItemRecycleView().setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                if (lastExpandedPosition == position) {
                    lastExpandedPosition = -1;
                } else {
                    int prevPosition = lastExpandedPosition;
                    lastExpandedPosition = position;
                    notifyItemChanged(prevPosition);
                }
                notifyItemChanged(position);
                onClickListener.onItemClick(order, holder.getOrderItemRecycleView());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder == null ? 0 : listOrder.size();
    }
}
