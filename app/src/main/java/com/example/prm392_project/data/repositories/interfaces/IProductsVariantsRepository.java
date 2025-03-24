package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.repositories.callback.CallBackData;

import java.util.List;

public interface IProductsVariantsRepository {
    LiveData<List<ProductVariants>> getProductVariants();

    LiveData<List<ProductVariants>> getVariantsByProductId(String productId);

    void getProductVariantsAsync(CallBackData<ProductVariants> callBack);

    void getProductVariantByIdAsync(String productId, String sizeId, CallBackData<ProductVariants> callBack);

    void insert(ProductVariants... productVariants);

    void update(ProductVariants productVariants);

    void delete(ProductVariants productVariants);
}
