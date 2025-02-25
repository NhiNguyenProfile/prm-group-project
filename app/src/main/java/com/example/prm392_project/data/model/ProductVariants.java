package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

import java.math.BigDecimal;

@Entity(tableName = "ProductVariants", foreignKeys = {
        @ForeignKey(entity = Products.class, parentColumns = "id", childColumns = "product_id"),
        @ForeignKey(entity = Sizes.class, parentColumns = "id", childColumns = "size_id")
}, indices = {
        @Index(value = "product_id"),
        @Index(value = "size_id")
})
public class ProductVariants extends BaseEntity {
    @ColumnInfo(name = "product_id")
    private String productId;
    @ColumnInfo(name = "color_id")
    private String colorId;
    @ColumnInfo(name = "size_id")
    private String sizeId;
    @ColumnInfo(name = "stock_quantity")
    private int stockQuantity;
    @ColumnInfo(name = "price")
    private BigDecimal price;

    public ProductVariants() {
        super();
    }

    @Ignore
    public ProductVariants(String productId, String colorId, String sizeId, int stockQuantity, BigDecimal price) {
        this();
        this.productId = productId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
