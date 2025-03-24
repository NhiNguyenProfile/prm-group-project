package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.CallBackData;

import java.util.List;

public interface IUserRepository {
    LiveData<List<Users>> getAllUser();

    void getAllUserAsync(CallBackData<Users> callBack);

    void getAccountByEmailAsync(String email, CallBackData<Users> callback);

    void getUserByIdAsync(String id, CallBackData<Users> callBack);

    void insertUser(Users... users);

    void updateUsers(Users user);

    void deleteUser(Users user);

}
