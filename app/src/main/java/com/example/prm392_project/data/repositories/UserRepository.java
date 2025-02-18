package com.example.prm392_project.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.core.BaseRepository;
import com.example.prm392_project.data.dao.UserDAO;
import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.interfaces.IUserRepository;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class UserRepository extends BaseRepository<Users> implements IUserRepository {
    private UserDAO userDao;

    public UserRepository(Context ctx) {
        super();
        userDao = DatabaseHelper.getInstance(ctx).userDao();
    }

    @Override
    public LiveData<List<Users>> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void insertUser(Users... users) {
        execute(() -> userDao.insertUser(users));
    }

    @Override
    public void deleteUser(Users users) {

    }
}
