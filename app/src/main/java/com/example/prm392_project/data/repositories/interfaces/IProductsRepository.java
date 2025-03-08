package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.repositories.callback.ProductCallBack;

import java.util.List;

public interface IProductsRepository {
    LiveData<List<Products>> getProducts();

    void getProductsAsync(ProductCallBack callBack);
}
