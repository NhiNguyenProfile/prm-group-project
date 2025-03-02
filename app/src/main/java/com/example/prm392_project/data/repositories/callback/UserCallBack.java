package com.example.prm392_project.data.repositories.callback;

import com.example.prm392_project.data.model.Users;

import java.util.List;

public abstract class UserCallBack {
    public void onGetUserByEmail(Users users) {
    }

    public void onGetListUser(List<Users> users) {
    }

    public void onGetUserById(Users user) {
    }
}
