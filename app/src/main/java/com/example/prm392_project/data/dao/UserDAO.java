package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.prm392_project.data.model.Users;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUser(); // dùng trong viewModel

    @Query("SELECT * FROM users")
    List<Users> getAllUserAsync(); // dùng dưới background

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Users... users);

    @Delete
    void deleteUser(Users users);
}
