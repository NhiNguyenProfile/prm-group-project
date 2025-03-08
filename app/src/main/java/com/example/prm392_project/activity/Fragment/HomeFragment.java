package com.example.prm392_project.activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.prm392_project.R;
import com.example.prm392_project.data.adapter.ImageSliderAdapter;
import com.example.prm392_project.data.adapter.ProductAdapter;
import com.example.prm392_project.data.view_model.ProductViewModel;
import com.example.prm392_project.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductViewModel productViewModel;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        init();
        return binding.getRoot();
    }


    private void init() {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        handleAllFunction();
        setupBanner();
    }

    private void setupBanner() {
        List<Integer> imageList = Arrays.asList(R.raw.banner1, R.raw.banner2);
        ImageSliderAdapter adapter = new ImageSliderAdapter(imageList);
        binding.viewPager.setAdapter(adapter);
    }

    private void handleAllFunction() {
        setupRecycleView();
    }

    private void setupRecycleView() {
        productAdapter = new ProductAdapter(product -> {
            Toast.makeText(requireContext(), product.getName(), Toast.LENGTH_SHORT).show();
        });
        binding.productRecycleView.setAdapter(productAdapter);
        binding.productRecycleView.setLayoutManager(new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false));
        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.setData(products);
            }
        });
    }
}