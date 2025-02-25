package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.Users;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users where is_del == false")
    LiveData<List<Users>> getAllUser(); // use to display

    @Query("SELECT * FROM users where is_del == false")
    List<Users> getAllUserAsync(); // use under background

    @Query("SELECT * FROM users WHERE id = :id and is_del == false")
    Users getUserByIdAsync(int id);

    @Query("SELECT * FROM users WHERE email = :email and password = :password and is_del == false")
    Users getAccountAsync(String email, String password);

    @Query("SELECT * FROM users WHERE email = :email and is_del == false")
    Users getUserByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Users... users);

    @Update
    void updateUser(Users users);

    @Delete
    void deleteUser(Users users);
}
