package com.example.rainbow;

public class Product {
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
    Product(String name, int price, String image){
        this.name = name;
        this.price = price;
        this.image = image;
    }
    private String name;
    private String category;
    private String description;
    private int price;
    private int quantityAvailable;
    private String barCode;
    private String image;

    // Add getters (and setters if needed)
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return image; }
}
