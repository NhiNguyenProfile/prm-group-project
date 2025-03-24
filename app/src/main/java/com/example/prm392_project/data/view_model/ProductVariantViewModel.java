package com.example.prm392_project.data.view_model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.repositories.ProductsVariantsRepository;
import com.example.prm392_project.data.repositories.callback.CallBackData;

import java.util.List;

public class ProductVariantViewModel extends AndroidViewModel {

    private final LiveData<List<ProductVariants>> productVariants;
    private final ProductsVariantsRepository repository;

    public ProductVariantViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsVariantsRepository(application);
        productVariants = repository.getProductVariants();
    }

    public LiveData<List<ProductVariants>> getProductVariants() {
        return productVariants;
    }

    public void getProductVariantBySizeAndProductIdAsync(String productId, String sizeId, CallBackData<ProductVariants> callBack) {
        repository.getProductVariantByIdAsync(productId, sizeId, callBack);
    }

    public LiveData<List<ProductVariants>> getVariantsByProductId(String productId) {

        return repository.getVariantsByProductId(productId);
    }

    public void insert(ProductVariants... productVariants) {
        repository.insert(productVariants);
    }
}
