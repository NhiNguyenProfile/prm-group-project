package com.example.prm392_project.data.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.UserDAO;
import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.interfaces.IUserRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.CallBack;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class UserRepository implements IUserRepository {

    private LiveData<List<Users>> users;
    private UserDAO userDAO;

    public UserRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        userDAO = database.userDao();
        users = userDAO.getAllUser();
    }

    @Override
    public LiveData<List<Users>> getAllUser() {
        return users;
    }

    @Override
    public void getAllUserAsync(CallBack<List<Users>> callback) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            List<Users> users = userDAO.getAllUserAsync();
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onResult(users);
            });
        });
    }

    @Override
    public Users getAccountAsync(String email, String password) {
        return userDAO.getAccountAsync(email, password);
    }

    @Override
    public Users getUserByIdAsync(int id) {
        return userDAO.getUserByIdAsync(id);
    }

    @Override
    public void insertUser(Users... users) {
        AppExecutors.getDatabaseExecutor().execute(() -> userDAO.insertUser(users));
    }

    @Override
    public void updateUsers(Users user) {
        AppExecutors.getDatabaseExecutor().execute(() -> userDAO.updateUser(user));
    }

    @Override
    public void deleteUser(Users user) {
        AppExecutors.getDatabaseExecutor().execute(() -> userDAO.deleteUser(user));
    }
}
