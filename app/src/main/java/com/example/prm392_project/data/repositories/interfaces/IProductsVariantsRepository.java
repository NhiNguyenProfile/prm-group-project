package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.ProductVariants;

import java.util.List;

public interface IProductsVariantsRepository {
    LiveData<List<ProductVariants>> getProductVariants();

    LiveData<List<ProductVariants>> getVariantsByProductId(String productId);
}
