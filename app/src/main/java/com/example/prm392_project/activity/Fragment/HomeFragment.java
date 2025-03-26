package com.example.prm392_project.activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prm392_project.R;
import com.example.prm392_project.activity.ProductDetail;
import com.example.prm392_project.data.adapter.CategoryHomePageAdapter;
import com.example.prm392_project.data.adapter.ImageSliderAdapter;
import com.example.prm392_project.data.adapter.ProductAdapter;
import com.example.prm392_project.data.view_model.CategoriesViewModel;
import com.example.prm392_project.data.view_model.ProductViewModel;
import com.example.prm392_project.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductViewModel productViewModel;
    private CategoriesViewModel categoryViewModel;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }


    private void init() {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        handleAllFunction();
    }

    private void handleAllFunction() {
        setupBanner();
        setupCategoryRecycleView();
        setupProductRecycleView();
        showAllProduct();
    }

    private void setupBanner() {
        List<Integer> imageList = Arrays.asList(R.raw.banner1, R.raw.banner2);
        ImageSliderAdapter adapter = new ImageSliderAdapter(imageList);
        binding.viewPager.setAdapter(adapter);
        binding.bannerProg.setVisibility(View.GONE);
    }

    private void setupCategoryRecycleView() {
        CategoryHomePageAdapter categoryHomePageAdapter = new CategoryHomePageAdapter(cate -> {
            productViewModel.getAllProductByCategory(cate.getId()).observe(getViewLifecycleOwner(), products -> {
                productAdapter.setData(products);
            });
        });
        binding.categoryProg.setVisibility(View.VISIBLE);
        binding.categoryRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.categoryRecycleView.setAdapter(categoryHomePageAdapter);
        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            if (categories != null) {
                categoryHomePageAdapter.setData(categories);
                binding.categoryProg.setVisibility(View.GONE);
            }
        });
    }

    private void showAllProduct() {
        binding.allBTN.setOnClickListener(v -> {
            productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
                productAdapter.setData(products);
            });
        });
    }

    private void setupProductRecycleView() {
        productAdapter = new ProductAdapter(product -> {
            Intent intent = new Intent(requireContext(), ProductDetail.class);
            intent.putExtra("PRODUCT_ID", product.getId());
            startActivity(intent);
        });
        binding.productProg.setVisibility(View.VISIBLE);
        binding.productRecycleView.setAdapter(productAdapter);
        binding.productRecycleView.setLayoutManager(new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false));
        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.setData(products);
                binding.productProg.setVisibility(View.GONE);
            }
        });
    }
}