package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.UserRepository;
import com.example.prm392_project.util.CallBack;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<Users>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUser();
    }

    public Users login(String email, String password) {
        return userRepository.getAccountAsync(email, password);
    }

    public LiveData<List<Users>> getAllUsers() {
        return allUsers;
    }

    public void getAllUserAsync(CallBack<List<Users>> users) {
        userRepository.getAllUserAsync(users);
    }
}
