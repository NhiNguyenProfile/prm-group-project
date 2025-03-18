package com.example.prm392_project.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.OrderItemsDAO;
import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.dto.OrderItemDTO;
import com.example.prm392_project.data.repositories.interfaces.IOrderItemsRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class OrderItemsRepository implements IOrderItemsRepository {

    LiveData<List<OrderItems>> listOrderItems;
    OrderItemsDAO orderItemsDAO;

    public OrderItemsRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        orderItemsDAO = database.orderItemsDAO();
    }


    @Override
    public LiveData<List<OrderItemDTO>> getOrderItemOfOrder(String orderId) {
        return orderItemsDAO.getOrderItemOfOrder(orderId);
    }

    @Override
    public void insert(OrderItems... orderItems) {
        AppExecutors.getDatabaseExecutor().execute(() -> orderItemsDAO.insert(orderItems));
    }

    @Override
    public void update(OrderItems orderItems) {
        AppExecutors.getDatabaseExecutor().execute(() -> orderItemsDAO.update(orderItems));
    }

    @Override
    public void delete(OrderItems orderItems) {
        AppExecutors.getDatabaseExecutor().execute(() -> orderItemsDAO.delete(orderItems));
    }
}
