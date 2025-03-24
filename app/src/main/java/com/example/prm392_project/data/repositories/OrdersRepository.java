package com.example.prm392_project.data.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.prm392_project.data.dao.OrdersDAO;
import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.repositories.callback.CallBackData;
import com.example.prm392_project.data.repositories.interfaces.IOrdersRepository;
import com.example.prm392_project.util.AppExecutors;
import com.example.prm392_project.util.DatabaseHelper;

import java.util.List;

public class OrdersRepository implements IOrdersRepository {

    private LiveData<List<Orders>> orders;
    private OrdersDAO ordersDAO;

    public OrdersRepository(Application application) {
        DatabaseHelper database = DatabaseHelper.getInstance(application);
        ordersDAO = database.ordersDAO();
        this.orders = ordersDAO.getAllOrders();
    }

    @Override
    public LiveData<List<Orders>> getOrders() {
        return orders;
    }

    @Override
    public LiveData<List<Orders>> getOrderOfUser(String userId) {
        return ordersDAO.getOrdersOfUser(userId);
    }

    @Override
    public void insert(Orders orders, CallBackData<Orders> callback) {
        AppExecutors.getDatabaseExecutor().execute(() -> {
            ordersDAO.insert(orders);
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onGetItemId(orders.getId());
            });
        });
    }
}
