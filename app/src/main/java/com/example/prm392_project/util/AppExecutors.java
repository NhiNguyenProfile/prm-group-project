package com.example.prm392_project.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final int THREAD_COUNT = 3;
    private static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    private AppExecutors() {
    }

    public static ExecutorService getDatabaseExecutor() {
        return databaseExecutor;
    }
}
