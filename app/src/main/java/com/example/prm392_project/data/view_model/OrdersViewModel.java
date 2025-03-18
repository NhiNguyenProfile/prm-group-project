package com.example.prm392_project.data.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.repositories.OrdersRepository;
import com.example.prm392_project.data.repositories.callback.OrderCallBack;
import com.example.prm392_project.data.repositories.interfaces.IOrdersRepository;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {
    LiveData<List<Orders>> listOrder;
    MutableLiveData<String> userId = new MutableLiveData<>();
    private IOrdersRepository repository;

    public OrdersViewModel(@NonNull Application application) {
        super(application);
        repository = new OrdersRepository(application);

        listOrder = Transformations.switchMap(userId, id -> {
            if (id == null || id.isEmpty()) {
                return repository.getOrders();
            } else {
                return repository.getOrderOfUser(id);
            }
        });
    }

    public void setUserId(String id) {
        userId.setValue(id);
    }

    public LiveData<List<Orders>> getOrders() {
        return listOrder;
    }

    public void insertOrder(Orders orders, OrderCallBack callBack) {
        repository.insert(orders, callBack);
    }
}
