package com.example.prm392_project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prm392_project.R;
import com.example.prm392_project.data.adapter.ImageSliderAdapter;
import com.example.prm392_project.data.adapter.SizeAdapter;
import com.example.prm392_project.data.model.CartItem;
import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.view_model.ProductVariantViewModel;
import com.example.prm392_project.data.view_model.ProductViewModel;
import com.example.prm392_project.databinding.ActivityProductDetailBinding;
import com.example.prm392_project.util.ManagementCart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetail extends AppCompatActivity {

    private String productId;
    private Products product;
    private List<String> currentSizeList;

    private ProductViewModel productViewModel;
    private ProductVariantViewModel productVariantViewModel;
    private ActivityProductDetailBinding binding;

    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        currentSizeList = new ArrayList<>();
        productId = getIntent().getStringExtra("PRODUCT_ID");

        if (productId != null) {
            productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
            productVariantViewModel = new ViewModelProvider(this).get(ProductVariantViewModel.class);
            loadProductData();
        } else {
            Toast.makeText(this, "Product not found!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        managementCart = new ManagementCart(this);
    }

    private void loadProductData() {
        productViewModel.getProductById(productId).observe(this, item -> {
            if (item != null) {
                product = item;
                updateUI();
            }
        });
    }

    private void topBarAction() {
        binding.topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_card) {
                    startActivity(new Intent(ProductDetail.this, CardActivity.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void init() {
        handelAllFunction();
    }

    private void handelAllFunction() {
        topBarAction();
        goBack();

    }

    private void goBack() {
        binding.topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void updateUI() {
        binding.titleTxt.setText(product.getName());
        binding.descriptionTxt.setText(product.getDescription());
        binding.priceTxt.setText("$" + product.getPrice());
        binding.ratingTxt.setText("5 Rating");
        setupBanners();
        initSizeLists();
        binding.addToCartBtn.setOnClickListener(v -> addToCart());
    }

    private void setupBanners() {
        List<Integer> imageList = Arrays.asList(product.getImageResId());
        ImageSliderAdapter adapter = new ImageSliderAdapter(imageList);
        binding.slider.setAdapter(adapter);
    }

    private void initSizeLists() {
        productVariantViewModel.getVariantsByProductId(productId).observe(this, sizeList -> {
            if (sizeList != null && !sizeList.isEmpty()) {

                List<String> sizeListOnly = new ArrayList<>();
                for (ProductVariants variant : sizeList) {
                    sizeListOnly.add(variant.getSize());
                }
                currentSizeList = sizeListOnly;

                SizeAdapter sizeAdapter = new SizeAdapter(sizeListOnly);
                binding.sizeList.setAdapter(sizeAdapter);
                binding.sizeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            } else {
                Toast.makeText(this, "No sizes available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("MyPrefs", this.MODE_PRIVATE);
        int selectedPosition = sharedPreferences.getInt("selectedPosition", 0);
        CartItem cartItem = new CartItem(productId, product.getName(), 1, product.getPrice(), product.getImageResId(), currentSizeList.get(selectedPosition));
        managementCart.insertItem(cartItem, this);
    }


}