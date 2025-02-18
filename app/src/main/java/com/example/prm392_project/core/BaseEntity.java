package com.example.prm392_project.core;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class BaseEntity {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    @ColumnInfo(name = "create_time")
    private Long createTime;
    @ColumnInfo(name = "delete_time")
    private Long deleteTime;

    public BaseEntity() {
        id = UUID.randomUUID().toString().replace("-", "");
        createTime = System.currentTimeMillis();
        deleteTime = System.currentTimeMillis();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }
}
