package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "products",
        foreignKeys = @ForeignKey(entity = Categories.class, parentColumns = "id", childColumns = "category_id"),
        indices = @Index(value = "category_id")
)
public class Products extends BaseEntity {
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "brand")
    private String brand;
    @ColumnInfo(name = "price")
    private Float price;
    @ColumnInfo(name = "imageUrl")
    private int imageResId;
    @ColumnInfo(name = "category_id")
    private String category_id;

    public Products() {
        super();
    }

    @Ignore
    public Products(String name, Float price, String description, String brand, int imageResId, String category_id) {
        this();
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.imageResId = imageResId;
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
