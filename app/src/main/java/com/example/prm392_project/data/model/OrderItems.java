package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "order_items",
        indices = {
                @Index(value = {"variant_id"}),
                @Index(value = "order_id")
        },
        foreignKeys = {
                @ForeignKey(entity = Orders.class, parentColumns = "id", childColumns = "order_id"),
                @ForeignKey(entity = ProductVariants.class, parentColumns = "id", childColumns = "variant_id")
        }
)
public class OrderItems extends BaseEntity {
    @ColumnInfo(name = "order_id")
    private String orderId;
    @ColumnInfo(name = "variant_id")
    private String variantId;
    @ColumnInfo(name = "quantity")
    private int quantity;
    @ColumnInfo(name = "price")
    private float price;

    public OrderItems() {
        super();
    }

    @Ignore
    public OrderItems(String orderId, String variantId, int quantity, float price) {
        this();
        this.orderId = orderId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
