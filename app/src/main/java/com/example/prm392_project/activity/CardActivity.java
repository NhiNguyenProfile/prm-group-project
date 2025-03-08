package com.example.prm392_project.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prm392_project.data.adapter.CartAdapter;
import com.example.prm392_project.databinding.ActivityCardBinding;
import com.example.prm392_project.util.ChangeNumberItemsListener;
import com.example.prm392_project.util.ManagementCart;

public class CardActivity extends AppCompatActivity {
    private ActivityCardBinding binding;
    private ManagementCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagementCart(this);

        setVariable();
        initCartList();
        calculateCart();
    }

    private void initCartList() {
        binding.viewCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.viewCart.setAdapter(new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void onChanged() {
                calculateCart();
            }
        }));

        // Toggle visibility for empty cart message or cart list
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollView2.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10.0;
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }

    private void setVariable() {
        goBack();
    }

    private void goBack() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
    }
}