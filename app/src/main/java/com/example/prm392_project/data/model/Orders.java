package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "orders",
        foreignKeys = {
                @ForeignKey(entity = Users.class, parentColumns = "id", childColumns = "user_id")
        },
        indices = {
                @Index(value = "user_id")
        }
)
public class Orders extends BaseEntity {
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "total_amount")
    private float totalAmount;
    @ColumnInfo(name = "status")
    private String status;

    public Orders() {
        super();
    }

    @Ignore
    public Orders(String userId, float totalAmount, String status) {
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

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
