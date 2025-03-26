package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.Products;

import java.util.List;

@Dao
public interface ProductsDAO {
    @Query("SELECT * FROM Products")
    LiveData<List<Products>> getAllProduct();

    @Query("SELECT * FROM Products")
    List<Products> getAllProductAsync();

    @Query("SELECT * FROM Products WHERE id = :id")
    Products getProductById(String id);

    @Query("SELECT * FROM products  where products.category_id = :categoryId")
    LiveData<List<Products>> getAllProductByCategory(String categoryId);

    @Insert
    void insert(Products... product);

    @Insert
    void insertProducts(List<Products> products);

    @Update
    void update(Products product);

    @Delete
    void delete(Products product);

}
