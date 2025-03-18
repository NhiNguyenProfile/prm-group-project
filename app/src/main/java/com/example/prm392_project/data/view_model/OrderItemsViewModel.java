package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.dto.OrderItemDTO;
import com.example.prm392_project.data.repositories.OrderItemsRepository;
import com.example.prm392_project.data.repositories.interfaces.IOrderItemsRepository;

import java.util.List;

public class OrderItemsViewModel extends AndroidViewModel {
    private LiveData<List<OrderItems>> listOrderItems;
    private IOrderItemsRepository repository;

    public OrderItemsViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderItemsRepository(application);
    }

    public LiveData<List<OrderItemDTO>> getOrderItemsOfOrder(String orderId) {
        return repository.getOrderItemOfOrder(orderId);
    }

    public void insertOrderItems(OrderItems orderItems) {
        repository.insert(orderItems);
    }
}
