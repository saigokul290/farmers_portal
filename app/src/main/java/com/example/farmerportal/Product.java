package com.example.farmerportal;

public class Product {
    private String category;
    private String price;
    private String quantity;

    // Required empty constructor for Firebase
    public Product() {
    }

    public Product(String category, String price, String quantity) {
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Add getter and setter methods
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
