package com.example.prm392_project.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.prm392_project.data.dao.UserDAO;
import com.example.prm392_project.data.model.Users;

@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract UserDAO userDao();

    private static volatile DatabaseHelper INSTANCE;

    public static DatabaseHelper getInstance(Context ctx) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(), DatabaseHelper.class, "prm392_project")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
