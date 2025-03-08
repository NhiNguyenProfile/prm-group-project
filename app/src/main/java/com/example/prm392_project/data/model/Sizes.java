package com.example.prm392_project.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sizes")
public class Sizes {
    @ColumnInfo(name = "size")
    @PrimaryKey
    @NonNull
    private String size;

    public Sizes() {
    }

    @Ignore
    public Sizes(String size) {
        this();
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
