package com.example.prm392_project.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.UserCallBack;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityProfileBinding;
import com.example.prm392_project.util.SessionManager;

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
        handleAllFunction();
    }

    private void handleAllFunction() {
        fetchUserData();
        updateProfile();
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
        binding.updateProfileBTN.setOnClickListener(v -> {
            Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show();
        });
    }
}