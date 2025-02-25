package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.Categories;

import java.util.List;

@Dao
public interface CategoriesDAO {
    @Query("select * from categories")
    LiveData<List<Categories>> getAllCategory();

    @Query("select * from categories")
    List<Categories> getAllCategoryAsync();

    @Delete
    void deleteCategory(Categories categories);

    @Insert
    void insertCategory(Categories... categories);

    @Update
    void updateCategory(Categories categories);
}
