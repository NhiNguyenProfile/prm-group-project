package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prm392_project.data.model.Orders;

import java.util.List;

@Dao
public interface OrdersDAO {
    @Query("SELECT * FROM orders")
    @Transaction
    LiveData<List<Orders>> getAllOrders();

    @Query("SELECT * FROM orders WHERE user_id = :userId")
    @Transaction
    LiveData<List<Orders>> getOrdersOfUser(String userId);

    @Insert
    void insert(Orders... orders);

    @Update
    void update(Orders orders);

    @Delete
    void delete(Orders orders);
}
