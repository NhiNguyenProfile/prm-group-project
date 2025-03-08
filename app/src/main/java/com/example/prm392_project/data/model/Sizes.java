package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sizes")
public class Sizes {
    @PrimaryKey
    @ColumnInfo(name = "size")
    private int size;

    @Ignore
    public Sizes(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
