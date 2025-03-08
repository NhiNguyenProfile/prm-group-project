package com.example.prm392_project.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.example.prm392_project.core.BaseEntity;

@Entity(tableName = "categories")
public class Categories extends BaseEntity {
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "logo")
    private int logo;
    @ColumnInfo(name = "description")
    private String description;

    public Categories() {
        super();
    }

    @Ignore
    public Categories(String name, String description, int logo) {
        this();
        this.name = name;
        this.description = description;
        this.logo = logo;
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

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
