package com.example.prm392_project.data.repositories.callback;

import com.example.prm392_project.data.model.ProductVariants;

import java.util.List;

public abstract class ProductVariantCallBack {
    public void onGetProductVariantsAsync(List<ProductVariants> productVariants) {
    }

    public void onGetProductVariantsById(String variantId) {

    }
}
