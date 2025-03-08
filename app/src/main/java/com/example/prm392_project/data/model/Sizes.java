package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "sizes")
public class Sizes extends BaseEntity {
    @ColumnInfo(name = "size")
    private String size;

    public Sizes() {
        super();
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
