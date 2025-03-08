package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.repositories.ProductsRepository;
import com.example.prm392_project.data.repositories.callback.ProductCallBack;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private LiveData<List<Products>> products;
    private ProductsRepository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
        products = repository.getProducts();
    }

    public LiveData<List<Products>> getProducts() {
        return products;
    }

    public LiveData<Products> getProductById(String id) {
        MutableLiveData<Products> productLiveData = new MutableLiveData<>();

        products.observeForever(productList -> {
            if (productList != null) {
                for (Products product : productList) {
                    if (product.getId().equals(id)) {
                        productLiveData.setValue(product);
                        break;
                    }
                }
            }
        });

        return productLiveData;
    }

    public void getProductsAsync(ProductCallBack callback) {
        repository.getProductsAsync(callback);
    }
}
