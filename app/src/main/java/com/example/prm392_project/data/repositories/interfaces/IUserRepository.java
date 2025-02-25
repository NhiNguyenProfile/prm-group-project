package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.util.CallBack;

import java.util.List;

public interface IUserRepository {
    LiveData<List<Users>> getAllUser();

    void getAllUserAsync(CallBack<List<Users>> users);

    Users getAccountAsync(String email, String password);

    Users getUserByIdAsync(int id);

    void insertUser(Users... users);

    void updateUsers(Users user);

    void deleteUser(Users user);

}
