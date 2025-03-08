package com.example.prm392_project.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.prm392_project.data.model.CartItem;
import com.example.prm392_project.databinding.ViewholderCartBinding;
import com.example.prm392_project.util.ChangeNumberItemsListener;
import com.example.prm392_project.util.ManagementCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<CartItem> listItemSelected;
    private Context context;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private ManagementCart managementCart;

    public CartAdapter(ArrayList<CartItem> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.managementCart = new ManagementCart(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItem item = listItemSelected.get(position);

        holder.binding.titleTxt.setText(item.getProductName());
        holder.binding.feeEachItem.setText("$" + item.getPrice() + " - Size " + item.getSize());
        holder.binding.totalEachItem.setText("$" + Math.round(item.getQuantity() * item.getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(item.getQuantity()));

        Glide.with(holder.itemView.getContext())
                .load(item.getImageResId())
                .apply(new RequestOptions().transform(new CenterCrop()))
                .into(holder.binding.pic);

        holder.binding.plusCartBtn.setOnClickListener(v -> {
            managementCart.plusItem(listItemSelected, position, new ChangeNumberItemsListener() {
                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                    if (changeNumberItemsListener != null) {
                        changeNumberItemsListener.onChanged();
                    }
                }
            });
        });

        holder.binding.minusCartBtn.setOnClickListener(v -> {
            managementCart.minusItem(listItemSelected, position, new ChangeNumberItemsListener() {
                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                    if (changeNumberItemsListener != null) {
                        changeNumberItemsListener.onChanged();
                    }
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewholderCartBinding binding;

        public ViewHolder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
