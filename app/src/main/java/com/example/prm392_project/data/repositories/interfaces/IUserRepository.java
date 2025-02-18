package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.core.BaseRepository;
import com.example.prm392_project.data.model.Users;

import java.util.List;

public interface IUserRepository {
    public LiveData<List<Users>> getAllUser();

    public void insertUser(Users... users);

    public void deleteUser(Users users);

}
