package com.example.prm392_project.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_project.databinding.ActivityCardBinding;

public class CardActivity extends AppCompatActivity {

    private ActivityCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        handelAllFunction();
    }

    private void handelAllFunction() {
        goBack();
    }

    private void goBack() {
        binding.topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}