package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.ProductVariants;

import java.util.List;

@Dao
public interface ProductsVariantsDAO {
    @Query("SELECT * FROM product_variants")
    LiveData<List<ProductVariants>> getAllProductVariants();

    @Query("SELECT id FROM product_variants WHERE product_id = :productId and size = :sizeId")
    String getProductVariantIdByProductIdAndSizeId(String productId, String sizeId);

    @Query("SELECT * FROM product_variants WHERE product_id = :productId")
    LiveData<List<ProductVariants>> getVariantsByProductId(String productId);

    @Query("SELECT * FROM product_variants")
    List<ProductVariants> getProductVariantsAsync();

    @Insert
    void insert(ProductVariants... productVariants);

    @Insert
    void insertProductVariants(List<ProductVariants> productVariants);

    @Update
    void update(ProductVariants productVariant);

    @Delete
    void delete(ProductVariants productVariant);
}
