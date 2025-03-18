package com.example.prm392_project.data.repositories.interfaces;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.repositories.callback.OrderCallBack;

import java.util.List;

public interface IOrdersRepository {
    LiveData<List<Orders>> getOrders();

    LiveData<List<Orders>> getOrderOfUser(String userId);

    void insert(Orders orders, OrderCallBack callBack);
}
