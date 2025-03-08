package com.example.prm392_project.data.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Categories;
import com.example.prm392_project.data.view_holder.CategoryHomePageViewHolder;
import com.example.prm392_project.databinding.ItemCategoryHomePageBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryHomePageAdapter extends RecyclerView.Adapter<CategoryHomePageViewHolder> {

    private List<Categories> categoryList = new ArrayList<>();

    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Categories category);
    }

    public void setData(List<Categories> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryHomePageAdapter(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCategories(List<Categories> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryHomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryHomePageBinding binding = ItemCategoryHomePageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryHomePageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomePageViewHolder holder, int position) {
        Categories category = categoryList.get(position);
        if (category == null) {
            return;
        }
        holder.bindData(category);
        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
