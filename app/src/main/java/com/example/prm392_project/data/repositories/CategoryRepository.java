package com.example.prm392_project.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.CategoriesDAO;
import com.example.prm392_project.data.model.Categories;
import com.example.prm392_project.data.repositories.interfaces.ICategoryRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryRepository implements ICategoryRepository {

    private CategoriesDAO categoryDAO;

    private LiveData<List<Categories>> categories;

    public CategoryRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        categoryDAO = database.categoriesDAO();
        categories = categoryDAO.getAllCategory();
    }

    @Override
    public LiveData<List<Categories>> getAllCategories() {
        return categories;
    }

    @Override
    public List<Categories> getAllCategoryAsync() {
        try {
            return AppExecutors.getDatabaseExecutor().submit(() -> categoryDAO.getAllCategoryAsync()).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertCategory(Categories... categories) {
        AppExecutors.getDatabaseExecutor().execute(() -> categoryDAO.insertCategory(categories));
    }

    @Override
    public void deleteCategory(Categories categories) {
        AppExecutors.getDatabaseExecutor().execute(() -> categoryDAO.deleteCategory(categories));
    }

    @Override
    public void updateCategory(Categories categories) {
        AppExecutors.getDatabaseExecutor().execute(() -> categoryDAO.updateCategory(categories));
    }
}
