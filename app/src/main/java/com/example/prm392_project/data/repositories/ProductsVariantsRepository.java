package com.example.prm392_project.data.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.ProductsVariantsDAO;
import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.repositories.callback.CallBackData;
import com.example.prm392_project.data.repositories.interfaces.IProductsVariantsRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class ProductsVariantsRepository implements IProductsVariantsRepository {
    private LiveData<List<ProductVariants>> productVariants;
    private ProductsVariantsDAO productsVariantsDAO;

    public ProductsVariantsRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        productsVariantsDAO = database.productVariantsDAO();
        this.productVariants = productsVariantsDAO.getAllProductVariants();
    }

    @Override
    public LiveData<List<ProductVariants>> getProductVariants() {
        return productVariants;
    }

    @Override
    public LiveData<List<ProductVariants>> getVariantsByProductId(String productId) {
        return productsVariantsDAO.getVariantsByProductId(productId);
    }

    @Override
    public void getProductVariantsAsync(CallBackData<ProductVariants> callBack) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            List<ProductVariants> productVariants = productsVariantsDAO.getProductVariantsAsync();
            new Handler(Looper.getMainLooper()).post(() -> {
                callBack.onGetAllItem(productVariants);
            });
        });
    }

    @Override
    public void getProductVariantByIdAsync(String productId, String sizeId, CallBackData<ProductVariants> callBack) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            String variantId = productsVariantsDAO.getProductVariantIdByProductIdAndSizeId(productId, sizeId);
            new Handler(Looper.getMainLooper()).post(() -> {
                callBack.onGetItemId(variantId);
            });

        });
    }

    @Override
    public void insert(ProductVariants... productVariants) {
        AppExecutors.getDatabaseExecutor().execute(() -> productsVariantsDAO.insert(productVariants));
    }

    @Override
    public void update(ProductVariants productVariants) {
        AppExecutors.getDatabaseExecutor().execute(() -> productsVariantsDAO.update(productVariants));
    }

    @Override
    public void delete(ProductVariants productVariants) {
        AppExecutors.getDatabaseExecutor().execute(() -> productsVariantsDAO.delete(productVariants));
    }
}

