package com.example.vynguyen.lab4_prob1;

import java.io.Serializable;

public class Product implements Serializable {
    String title;
    double price;
    String color;
    Integer image;
    String itemID;
    String description;

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public Integer getImage() {
        return image;
    }

    public String getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product(String title, double price, String color, Integer image, String itemID, String description) {
        this.title = title;
        this.price = price;
        this.color = color;
        this.image = image;
        this.itemID = itemID;
        this.description = description;
    }
}
