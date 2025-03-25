package com.example.prm392_project.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.CallBackData;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityProfileBinding;
import com.example.prm392_project.util.SessionManager;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private UserViewModel userViewModel;
    private Users user;

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
        String userId = SessionManager.getUserId(getApplicationContext());
        userViewModel.getUserInformation(userId, new CallBackData<Users>() {
            @Override
            public void onGetItem(Users users) {
                super.onGetItem(users);
                bindingUserData(users);
                setUser(users);
            }
        });
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private void bindingUserData(Users user) {
        binding.fullNameEDT.setText(user.getName());
        binding.emailEDT.setText(user.getEmail());
        binding.addressEDT.setText(user.getAddress());
        binding.phoneEDT.setText(user.getPhoneNumber());
    }

    private void updateUser() {
        String fullName = Objects.requireNonNull(binding.fullNameEDT.getText()).toString();
        String email = Objects.requireNonNull(binding.emailEDT.getText()).toString();
        String address = Objects.requireNonNull(binding.addressEDT.getText()).toString();
        String phone = Objects.requireNonNull(binding.phoneEDT.getText()).toString();
        String password = Objects.requireNonNull(binding.newPasswordEDT.getText()).toString();
        user.setName(fullName);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(phone);
        user.setPassword(password);
        userViewModel.updateUser(user);
        Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            finish();
        }, 2000);
    }

    private void updateProfile() {
        binding.updateProfileBTN.setOnClickListener(v -> {
            updateUser();
        });
    }
}