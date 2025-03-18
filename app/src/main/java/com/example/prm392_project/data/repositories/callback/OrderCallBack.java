package com.example.prm392_project.data.repositories.callback;

import com.example.prm392_project.data.model.Orders;

import java.util.List;

public abstract class OrderCallBack {
    public void onGetOrdersAsync(List<Orders> orders) {

    }

    public void onInsertAsync(String id) {

    }
}
