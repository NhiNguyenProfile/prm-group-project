package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.Sizes;

import java.util.List;

@Dao
public interface SizesDAO {

    @Query("SELECT * FROM sizes")
    LiveData<List<Sizes>> getAllSizes();

    @Query("SELECT * FROM sizes")
    List<Sizes> getAllSizesAsync();

    @Insert
    void insert(Sizes... size);

    @Insert
    void insertSizes(List<Sizes> sizes);

    @Update
    void update(Sizes size);

    @Delete
    void delete(Sizes size);
}
