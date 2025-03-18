package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "product_variants", foreignKeys = {
        @ForeignKey(entity = Products.class, parentColumns = "id", childColumns = "product_id"),
        @ForeignKey(entity = Sizes.class, parentColumns = "size", childColumns = "size")
}, indices = {
        @Index(value = "product_id"),
        @Index(value = "size")
})
public class ProductVariants extends BaseEntity {
    @ColumnInfo(name = "product_id")
    private String productId;
    @ColumnInfo(name = "size")
    private String size;
    @ColumnInfo(name = "stock_quantity")
    private int stockQuantity;

    public ProductVariants() {
        super();
    }

    @Ignore
    public ProductVariants(String productId, String size, int stockQuantity) {
        this();
        this.productId = productId;
        this.size = size;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
