package com.example.prm392_project.core;

import com.example.prm392_project.util.AppExecutors;

import java.util.concurrent.ExecutorService;

public abstract class BaseRepository<T> {
    protected final ExecutorService executorService;

    public BaseRepository() {
        this.executorService = AppExecutors.getDatabaseExecutor();
    }
    protected void execute(Runnable task) {
        executorService.execute(task);
    }
}
