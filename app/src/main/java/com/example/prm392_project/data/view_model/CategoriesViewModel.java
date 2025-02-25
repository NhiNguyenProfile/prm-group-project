package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Categories;
import com.example.prm392_project.data.repositories.CategoryRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {
    private final CategoryRepository categoryRepository;
    private final LiveData<List<Categories>> allCategories;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();
    }

    public LiveData<List<Categories>> getAllCategories() {
        return allCategories;
    }

    public void insertCategory(Categories... categories) {
        categoryRepository.insertCategory(categories);
    }

    public void deleteCategory(Categories categories) {
        categoryRepository.deleteCategory(categories);
    }

    public void updateCategory(Categories categories) {
        categoryRepository.updateCategory(categories);
    }
}
