package com.nghiepnguyen.lession5;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    String title;
    double price;
    String color;
    int imageResource;
    String itemid;
    String desc; // Description of the product

    public Product(String title, double price, String color, int imageResource, String itemid, String desc) {
        this.title = title;
        this.price = price;
        this.color = color;
        this.imageResource = imageResource;
        this.itemid = itemid;
        this.desc = desc;
    }

    protected Product(Parcel in) {
        title = in.readString();
        price = in.readDouble();
        color = in.readString();
        imageResource = in.readInt();
        itemid = in.readString();
        desc = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeDouble(price);
        parcel.writeString(color);
        parcel.writeInt(imageResource);
        parcel.writeString(itemid);
        parcel.writeString(desc);
    }
}
