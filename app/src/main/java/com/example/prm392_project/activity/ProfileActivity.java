package com.example.prm392_project.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.UserCallBack;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityProfileBinding;
import com.example.prm392_project.util.SessionManager;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        handleAllFunction();
    }

    private void handleAllFunction() {
        fetchUserData();
        updateProfile();
        goBack();
    }

    private void goBack() {
        binding.goBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void fetchUserData() {
        String userId = SessionManager.getInstance().getUserId();
        userViewModel.getUserInformation(userId, new UserCallBack() {
            @Override
            public void onGetUserById(Users user) {
                super.onGetUserById(user);
                bindingUserData(user);
            }
        });
    }

    private void bindingUserData(Users user) {
        binding.fullNameEDT.setText(user.getName());
        binding.emailEDT.setText(user.getEmail());
        binding.addressEDT.setText(user.getAddress());
        binding.phoneEDT.setText(user.getPhoneNumber());
    }

    private void updateProfile() {
        String fullName = Objects.requireNonNull(binding.fullNameEDT.getText()).toString();
        String email = Objects.requireNonNull(binding.emailEDT.getText()).toString();
        String address = Objects.requireNonNull(binding.addressEDT.getText()).toString();
        String phone = Objects.requireNonNull(binding.phoneEDT.getText()).toString();
        String password = Objects.requireNonNull(binding.newPasswordEDT.getText()).toString();
        binding.updateProfileBTN.setOnClickListener(v -> {
            Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show();
        });
    }
}