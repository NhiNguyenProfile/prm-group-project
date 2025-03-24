package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.UserRepository;
import com.example.prm392_project.data.repositories.callback.CallBackData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<Users>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUser();
    }

    public LiveData<List<Users>> getAllUsers() {
        return allUsers;
    }

    public void getUserInformation(String userId, CallBackData<Users> callBack) {
        userRepository.getUserByIdAsync(userId, callBack);
    }

    public void getAccountByEmailAsync(String email, CallBackData<Users> callBack) {
        userRepository.getAccountByEmailAsync(email, callBack);
    }

    public void registerAccount(Users user) {
        userRepository.insertUser(user);
    }

    public void getAllUserAsync(CallBackData<Users> callBack) {
        userRepository.getAllUserAsync(callBack);
    }

    public void updateUser(Users user) {
        userRepository.updateUsers(user);
    }
}
