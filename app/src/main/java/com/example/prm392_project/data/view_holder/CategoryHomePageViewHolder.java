package com.example.prm392_project.data.view_holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.model.Categories;
import com.example.prm392_project.databinding.ItemCategoryHomePageBinding;

public class CategoryHomePageViewHolder extends RecyclerView.ViewHolder {
    ItemCategoryHomePageBinding binding;

    public CategoryHomePageViewHolder(ItemCategoryHomePageBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindData(Categories category) {
        binding.categoryImage.setImageResource(category.getLogo());
    }
}
