package com.app.testapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for All, Men and Women data.
 */
public class CategoryDataModel {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("num_likes")
    private int likes;
    @SerializedName("num_comments")
    private int comments;
    @SerializedName("price")
    private int price;
    @SerializedName("photo")
    private String photoUrl;

    public CategoryDataModel(String id, String name, String status, int likes, int comments, int price, String photoUrl) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.likes = likes;
        this.comments = comments;
        this.price = price;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
