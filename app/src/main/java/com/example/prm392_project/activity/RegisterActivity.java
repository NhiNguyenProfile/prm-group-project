package com.example.prm392_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.data.model.Users;
import com.example.prm392_project.data.repositories.callback.CallBackData;
import com.example.prm392_project.data.repositories.validation.BlankValidator;
import com.example.prm392_project.data.repositories.validation.EmailValidator;
import com.example.prm392_project.data.view_model.UserViewModel;
import com.example.prm392_project.databinding.ActivityRegisterBinding;
import com.example.prm392_project.util.InputValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserViewModel userViewModel;

    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    protected void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        handleAllFunction();
    }

    protected void handleAllFunction() {
        registerAccount();
        goBackLogin();
        displayErrorMessage();
        goBack();
    }

    private void goBack() {
        binding.goBack.setOnClickListener(v -> {
            this.finish();
        });
    }

    protected void displayErrorMessage() {
        binding.fullNameEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.fullNameEDT, Collections.singletonList(new BlankValidator())));
        binding.emailEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.emailEDT, Arrays.asList(new BlankValidator(), new EmailValidator())));
        binding.phoneEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.phoneEDT, Collections.singletonList(new BlankValidator())));
        binding.addressEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.addressEDT, Collections.singletonList(new BlankValidator())));
        binding.passwordEDT.addTextChangedListener(InputValidator.getValidationWatcher(binding.passwordEDT, Collections.singletonList(new BlankValidator())));
    }

    protected boolean validateInput() {
        boolean isFullNameValid = InputValidator.validateField(binding.fullNameEDT, Collections.singletonList(new BlankValidator()));
        boolean isEmailValid = InputValidator.validateField(binding.emailEDT, Arrays.asList(new BlankValidator(), new EmailValidator()));
        boolean isPhoneValid = InputValidator.validateField(binding.phoneEDT, Collections.singletonList(new BlankValidator()));
        boolean isAddressValid = InputValidator.validateField(binding.addressEDT, Collections.singletonList(new BlankValidator()));
        boolean isPasswordValid = InputValidator.validateField(binding.passwordEDT, Collections.singletonList(new BlankValidator()));
        return isFullNameValid && isEmailValid && isPhoneValid && isAddressValid && isPasswordValid;
    }

    protected void goBackLogin() {
        binding.loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void registerAccount() {
        binding.registerBTN.setOnClickListener(v -> {
            if (!validateInput()) return;
            String fullName = Objects.requireNonNull(binding.fullNameEDT.getText()).toString();
            String email = Objects.requireNonNull(binding.emailEDT.getText()).toString();
            String phoneNumber = Objects.requireNonNull(binding.phoneEDT.getText()).toString();
            String address = Objects.requireNonNull(binding.addressEDT.getText()).toString();
            String password = Objects.requireNonNull(binding.passwordEDT.getText()).toString();
            userViewModel.getAccountByEmailAsync(email, new CallBackData<Users>() {
                @Override
                public void onGetItem(Users items) {
                    super.onGetItem(items);
                    if (users == null) {
                        Users user = new Users(fullName, email, password, phoneNumber, address, "Users");
                        userViewModel.registerAccount(user);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Email has register on system.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}