package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Categories;

import java.util.List;

public interface ICategoryRepository {
    LiveData<List<Categories>> getAllCategories();

    List<Categories> getAllCategoryAsync();

    void insertCategory(Categories... categories);

    void deleteCategory(Categories categories);

    void updateCategory(Categories categories);
}
