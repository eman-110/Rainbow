package com.example.rainbow.models;

public class Product {
    private String id;
    private String name;
    private String price;
    private String image;
    private String category;

    // Default constructor (needed for some libraries like Gson)
    public Product() {
    }

    // Constructor with all fields
    public Product(String id, String name, String price, String image, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    // Optional: You can add setters if you want to allow updates to the object
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}