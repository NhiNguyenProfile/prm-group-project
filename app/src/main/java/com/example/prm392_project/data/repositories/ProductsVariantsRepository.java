package com.example.prm392_project.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.ProductsVariantsDAO;
import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.repositories.interfaces.IProductsVariantsRepository;
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
}

