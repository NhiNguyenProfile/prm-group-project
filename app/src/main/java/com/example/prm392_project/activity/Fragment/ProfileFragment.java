package com.example.prm392_project.activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prm392_project.activity.LoginActivity;
import com.example.prm392_project.activity.ProfileActivity;
import com.example.prm392_project.databinding.FragmentProfileBinding;
import com.example.prm392_project.util.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(requireContext(), gso);
        handleAllFunction();
    }

    private void handleAllFunction() {
        onButtonClick();
        logout();
    }

    private void logout() {
        binding.logoutBTN.setOnClickListener(v -> {
            gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    SessionManager.getInstance().logout();
                    startActivity(new Intent(requireContext(), LoginActivity.class));
                }
            });
        });
    }

    private void onButtonClick() {
        binding.profile.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), ProfileActivity.class));
        });
    }

}