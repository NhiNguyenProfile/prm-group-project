package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.dto.OrderItemDTO;

import java.util.List;

public interface IOrderItemsRepository {

    LiveData<List<OrderItemDTO>> getOrderItemOfOrder(String orderId);

    void insert(OrderItems... orderItems);

    void update(OrderItems orderItems);

    void delete(OrderItems orderItems);
}
