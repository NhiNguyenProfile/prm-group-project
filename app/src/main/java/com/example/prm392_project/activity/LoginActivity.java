package com.example.prm392_project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 100;

    private final ActivityResultLauncher<Intent> googleSignInLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        if (account != null) {
                            userViewModel.getAccountByEmailAsync(account.getEmail(), new UserCallBack() {
                                @Override
                                public void onGetUserByEmail(Users users) {
                                    super.onGetUserByEmail(users);
                                    if (users != null) {
                                        loginSuccess();
                                    } else {
                                        Users user = new Users();
                                        user.setId(account.getId());
                                        user.setEmail(account.getEmail());
                                        user.setName(account.getDisplayName());
                                        userViewModel.registerAccount(user);
                                        loginSuccess();
                                    }
                                }
                            });
                            SessionManager.getInstance().setLogin(true, account.getId());
                        }
                    } catch (ApiException e) {
                        Log.e("GoogleSignIn", "Sign-in failed", e);
                    }
                }
            });

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
        setUpGoogle();

        login();
        register();
        loginWithGoogle();

        displayErrorMessage();
        goBack();
    }

    private void setUpGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount gsc = GoogleSignIn.getLastSignedInAccount(this);
        if (gsc != null) {
            loginSuccess();
        }
    }

    private void loginSuccess() {
        Intent resultIntent = new Intent();

        String previousScreen = getIntent().getStringExtra("previousScreen");

        if (previousScreen != null) {
            resultIntent.putExtra("previousScreen", previousScreen);
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void goBack() {
        binding.goBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void register() {
        binding.registerLink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
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
                        Intent resultIntent = new Intent();

                        String previousScreen = getIntent().getStringExtra("previousScreen");

                        if (previousScreen != null) {
                            resultIntent.putExtra("previousScreen", previousScreen);
                        }

                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }
            });
        });
    }

    private void loginWithGoogle() {
        binding.googleBTN.setOnClickListener(v -> {
            Intent signInGoogle = googleSignInClient.getSignInIntent();
            googleSignInLauncher.launch(signInGoogle);
        });
    }
}