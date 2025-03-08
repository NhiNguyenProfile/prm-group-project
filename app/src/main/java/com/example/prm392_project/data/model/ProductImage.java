package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "product_image",
        foreignKeys = @ForeignKey(entity = Products.class, parentColumns = "id", childColumns = "product_id"),
        indices = @Index(value = "product_id"))
public class ProductImage extends BaseEntity {

    @ColumnInfo(name = "image_url")
    private String image_url;
    @ColumnInfo(name = "product_id")
    private String product_id;

    public ProductImage() {
        super();
    }

    public ProductImage(String image_url, String product_id) {
        super();
        this.image_url = image_url;
        this.product_id = product_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
