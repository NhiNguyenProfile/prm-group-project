package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.prm392_project.data.model.ProductImage;

import java.util.List;

@Dao
public interface ProductImageDAO {
    @Query("SELECT * FROM ProductImage")
    List<ProductImage> getAllProductImagesAsync();

    @Query("SELECT * FROM ProductImage")
    LiveData<List<ProductImage>> getAllProductImages();

    @Insert
    void insert(ProductImage... productImage);

    @Insert
    void update(ProductImage productImage);

    @Insert
    void delete(ProductImage productImage);
}
