package com.example.prm392_project.data.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.databinding.ViewholderSizeBinding;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.Viewholder> {

    private List<String> items;
    private int selectedPosition = 0;
    private Context context;

    public SizeAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSizeBinding binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.binding.sizeTxt.setText(items.get(position));

        holder.binding.getRoot().setOnClickListener(v -> {
            int lastSelectedPosition = selectedPosition;
            selectedPosition = holder.getBindingAdapterPosition();
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);

            // Lưu selectedPosition vào SharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selectedPosition", selectedPosition);
            editor.apply();
        });

        if (selectedPosition == holder.getBindingAdapterPosition()) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected);
            holder.binding.sizeTxt.setTextColor(ContextCompat.getColor(context, R.color.purple));
        } else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg);
            holder.binding.sizeTxt.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private final ViewholderSizeBinding binding;

        public Viewholder(ViewholderSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}