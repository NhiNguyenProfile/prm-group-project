package com.example.prm392_project.data.repositories.callback;

import java.util.List;

public abstract class CallBackData<T> {
    public void onSuccess(T data) {

    }

    public void onError(String errorMessage) {

    }

    public void onGetAllItem(List<T> items) {

    }

    public void onGetItem(T items) {

    }

    public void onGetItemId(String id) {

    }
}
