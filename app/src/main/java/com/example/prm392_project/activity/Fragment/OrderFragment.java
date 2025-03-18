package com.example.prm392_project.activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.data.adapter.OrderAdapter;
import com.example.prm392_project.data.adapter.OrderItemAdapter;
import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.view_model.OrderItemsViewModel;
import com.example.prm392_project.data.view_model.OrdersViewModel;
import com.example.prm392_project.databinding.FragmentOrderBinding;
import com.example.prm392_project.util.SessionManager;

public class OrderFragment extends Fragment {
    private FragmentOrderBinding binding;
    private OrdersViewModel ordersViewModel;
    private OrderItemsViewModel orderItemsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }

    public void init() {
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        orderItemsViewModel = new ViewModelProvider(this).get(OrderItemsViewModel.class);
        handleAllFunction();
    }

    private void handleAllFunction() {
        setupRecycleView();
    }

    private void setupRecycleView() {
        OrderAdapter orderAdapter = new OrderAdapter(new OrderAdapter.OnClickListener() {
            OrderItemAdapter orderItemAdapter = new OrderItemAdapter();

            @Override
            public void onItemClick(Orders order, RecyclerView orderItemRecycleView) {
                orderItemsViewModel.getOrderItemsOfOrder(order.getId()).observe(getViewLifecycleOwner(), orderItems -> {
                    orderItemRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
                    orderItemRecycleView.setAdapter(orderItemAdapter);
                    orderItemAdapter.setData(orderItems);
                });
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(orderAdapter);
        ordersViewModel.setUserId(SessionManager.getInstance().getUserId());
        ordersViewModel.getOrders().observe(getViewLifecycleOwner(), orderAdapter::setData);
    }
}