package com.example.prm392_project.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.UserRepository;
import com.example.prm392_project.data.repositories.interfaces.IUserRepository;
import com.example.prm392_project.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding userBinding;
    private String firstName, lastName, email, phoneNumber, address;

    private IUserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBinding = ActivityUserBinding.inflate(getLayoutInflater());
        View view = userBinding.getRoot();
        setContentView(view);
        userRepository = new UserRepository(this);
        HandleAllFunction();
    }

    private void HandleAllFunction() {
        OnSubmitButtonClick();
        OnGetButtonClick();
    }

    private void GetInputValues() {
        firstName = userBinding.firstNameEDT.getText().toString().trim();
        lastName = userBinding.lastNameEDT.getText().toString().trim();
        email = userBinding.emailEDT.getText().toString().trim();
        phoneNumber = userBinding.phoneNumberEDT.getText().toString().trim();
        address = userBinding.addressEDT.getText().toString().trim();
    }

    private void OnSubmitButtonClick() {
        userBinding.submitButton.setOnClickListener(v -> {
            GetInputValues();
            userRepository.insertUser(new Users(firstName, lastName));
        });
    }

    private void OnGetButtonClick() {
        userBinding.getButton.setOnClickListener(v -> {
            userRepository.getAllUser().observe(this, users -> {
                if (users != null || !users.isEmpty()) {
                    for (Users user : users) {
                        Toast.makeText(this, user.getFirstName(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Không có người  dùng", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}