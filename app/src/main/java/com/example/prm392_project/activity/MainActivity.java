package com.example.prm392_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.R;
import com.example.prm392_project.activity.Fragment.HomeFragment;
import com.example.prm392_project.activity.Fragment.OrderFragment;
import com.example.prm392_project.activity.Fragment.ProfileFragment;
import com.example.prm392_project.data.view_model.ProductViewModel;
import com.example.prm392_project.databinding.ActivityMainBinding;
import com.example.prm392_project.util.SessionManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        handleAllFunction();
    }

    private void handleAllFunction() {
        navigationFragment();
        searchProduct();
        topBarAction();
    }

    private void topBarAction() {
        binding.topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_card) {
                    Toast.makeText(MainActivity.this, "Card clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, CardActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    private void navigationFragment() {
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                binding.topAppBar.setTitle("Home");
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.favorite) {
                binding.topAppBar.setTitle("Favourite");
            } else if (item.getItemId() == R.id.orders) {
                binding.topAppBar.setTitle("Orders");
                replaceFragment(new OrderFragment());
            } else if (item.getItemId() == R.id.account) {
                if (SessionManager.getInstance().isLoggedIn()) {
                    binding.topAppBar.setTitle("Profile");
                    replaceFragment(new ProfileFragment());
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
            }
            return true;
        });
    }

    private void searchProduct() {
        binding.searchBTN.setOnClickListener(v -> {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }
}