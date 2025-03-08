package com.example.prm392_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.UserCallBack;
import com.example.prm392_project.data.repositories.validation.BlankValidator;
import com.example.prm392_project.data.repositories.validation.EmailValidator;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityLoginBinding;
import com.example.prm392_project.util.InputValidator;
import com.example.prm392_project.util.SessionManager;

import java.util.Arrays;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        handleAllFunction();
    }

    private void handleAllFunction() {
        login();
        register();
        displayErrorMessage();
        goBack();
    }

    private void goBack() {
        binding.goBack.setOnClickListener(v -> {
            this.finish();
        });
    }

    private void register() {
        binding.registerLink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            this.finish();
        });
    }

    private void displayErrorMessage() {
        binding.emailEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.emailEDT, Arrays.asList(new EmailValidator(), new BlankValidator())));
        binding.passwordEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.passwordEDT, Collections.singletonList(new BlankValidator())));
    }

    private boolean validationInput() {
        boolean isEmailValid = InputValidator.validateField(binding.emailEDT, Arrays.asList(new EmailValidator(), new BlankValidator()));
        boolean isPasswordValid = InputValidator.validateField(binding.passwordEDT, Collections.singletonList(new BlankValidator()));

        return isEmailValid && isPasswordValid;
    }

    private void login() {
        binding.loginButton.setOnClickListener(v -> {
            if (!validationInput()) return;
            String email = binding.emailEDT.getText().toString();
            String password = binding.passwordEDT.getText().toString();
            userViewModel.getAccountByEmailAsync(email, new UserCallBack() {
                @Override
                public void onGetUserByEmail(Users users) {
                    super.onGetUserByEmail(users);
                    if (users == null || !users.getPassword().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Invalid password or email", Toast.LENGTH_SHORT).show();
                    } else {
                        SessionManager.getInstance().setLogin(true, users.getId());
                        finish();
                    }
                }
            });
        });
    }
}