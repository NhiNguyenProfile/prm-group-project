package com.example.prm392_project.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.R;
import com.example.prm392_project.data.adapter.UserAdapter;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityLoginBinding;
import com.example.prm392_project.util.InputValidator;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        handleAllFunction();
    }

    private void handleAllFunction() {
        showHidePassword();
        login();
    }

    private void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showHidePassword() {
        binding.visibleToggle.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                binding.passwordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.visibleToggle.setBackgroundResource(R.drawable.visibility_24px);
            } else {
                binding.passwordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.visibleToggle.setBackgroundResource(R.drawable.visibility_off_24px);
            }

            binding.passwordET.setSelection(binding.passwordET.getText().length());
        });
    }

    private void login() {
        binding.loginButton.setOnClickListener(v -> {
            if (!InputValidator.validateInputBlank(binding.emailET) || !InputValidator.validateInputBlank(binding.passwordET)) {
                return;
            }
            String email = binding.emailET.getText().toString();
            String password = binding.passwordET.getText().toString();
            userViewModel.login(email, password);
        });
    }
}