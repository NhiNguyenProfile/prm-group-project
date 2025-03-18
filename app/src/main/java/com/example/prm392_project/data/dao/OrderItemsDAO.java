package com.example.prm392_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.dto.OrderItemDTO;

import java.util.List;

@Dao
public interface OrderItemsDAO {
    @Query("SELECT p.name as productName, " +
            "p.imageUrl as productImage, " +
            "oi.quantity as quantity, " +
            "pv.size as size," +
            "p.price as price, " +
            "o.create_time as orderDate " +
            "FROM orders o " +
            "JOIN order_items oi ON oi.order_id = o.id " +
            "JOIN product_variants pv ON pv.id = oi.variant_id " +
            "JOIN products p ON p.id = pv.product_id " +
            "WHERE oi.order_id = :orderId")
    LiveData<List<OrderItemDTO>> getOrderItemOfOrder(String orderId);

    @Insert
    void insert(OrderItems... orderItems);

    @Update
    void update(OrderItems orderItems);

    @Delete
    void delete(OrderItems orderItems);
}
