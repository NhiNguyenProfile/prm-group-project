package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.repositories.callback.CallBackData;

import java.util.List;

public interface IProductsRepository {
    LiveData<List<Products>> getProducts();

    LiveData<List<Products>> getAllProductByCategory(String categoryId);

    void getProductsAsync(CallBackData<Products> callBack);
}
