package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.UserCallBack;

import java.util.List;

public interface IUserRepository {
    LiveData<List<Users>> getAllUser();

    void getAllUserAsync(UserCallBack callBack);

    void getAccountByEmailAsync(String email, UserCallBack callback);

    void getUserByIdAsync(String id, UserCallBack callBack);

    void insertUser(Users... users);

    void updateUsers(Users user);

    void deleteUser(Users user);

}
