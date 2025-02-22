package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "Orders", foreignKeys = @ForeignKey(entity = Users.class, parentColumns = "id", childColumns = "user_id"))
public class Orders extends BaseEntity {
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "total_amount")
    private String totalAmount;
    @ColumnInfo(name = "status")
    private String status;

    public Orders() {
        super();
    }

    @Ignore
    public Orders(String userId, String totalAmount, String status) {
        this();
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
