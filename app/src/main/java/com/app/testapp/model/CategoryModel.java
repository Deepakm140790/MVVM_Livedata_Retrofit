package com.app.testapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for Category.
 */
public class CategoryModel {
    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private String data;

    public CategoryModel(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
