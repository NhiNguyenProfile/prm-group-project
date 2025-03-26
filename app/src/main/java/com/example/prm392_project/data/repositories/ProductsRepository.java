package com.example.prm392_project.data.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.ProductsDAO;
import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.repositories.callback.CallBackData;
import com.example.prm392_project.data.repositories.interfaces.IProductsRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class ProductsRepository implements IProductsRepository {
    private LiveData<List<Products>> products;
    private ProductsDAO productsDAO;

    public ProductsRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        productsDAO = database.productsDAO();
        this.products = productsDAO.getAllProduct();
    }

    @Override
    public LiveData<List<Products>> getProducts() {
        return products;
    }

    @Override
    public LiveData<List<Products>> getAllProductByCategory(String categoryId) {
        return productsDAO.getAllProductByCategory(categoryId);
    }

    @Override
    public void getProductsAsync(CallBackData<Products> callBack) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            List<Products> productAsync = productsDAO.getAllProductAsync();
            new Handler(Looper.getMainLooper()).post(() -> {
                callBack.onGetAllItem(productAsync);
            });
        });
    }
}
