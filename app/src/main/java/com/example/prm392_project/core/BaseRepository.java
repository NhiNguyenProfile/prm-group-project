package com.example.prm392_project.core;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.util.AppExecutors;

import java.util.concurrent.ExecutorService;

public abstract class BaseRepository<T> {
    protected final ExecutorService executorService;
    protected final MutableLiveData<T> data = new MutableLiveData<>();

    public BaseRepository() {
        this.executorService = AppExecutors.getDatabaseExecutor();
    }

    public LiveData<T> getData() {
        return data;
    }

    protected void execute(Runnable task) {
        executorService.execute(task);
    }
}
