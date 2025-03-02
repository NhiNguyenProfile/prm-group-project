package com.example.prm392_project.data.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.UserDAO;
import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.UserCallBack;
import com.example.prm392_project.data.repositories.interfaces.IUserRepository;
import com.example.prm392_project.util.AppExecutors;
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
    public void getAllUserAsync(UserCallBack callback) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            List<Users> users = userDAO.getAllUserAsync();
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onGetListUser(users);
            });
        });
    }

    @Override
    public void getAccountByEmailAsync(String email, UserCallBack callBack) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            Users user = userDAO.getUserByEmail(email);
            new Handler(Looper.getMainLooper()).post(() -> callBack.onGetUserByEmail(user));
        });
    }

    @Override
    public void getUserByIdAsync(String id, UserCallBack callBack) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            Users user = userDAO.getUserByIdAsync(id);
            new Handler(Looper.getMainLooper()).post(() -> callBack.onGetUserById(user));
        });
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
