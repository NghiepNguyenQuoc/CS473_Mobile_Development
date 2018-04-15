package com.nghiepnguyen.personalizedwebapp;

/**
 * Created by 986375 on 4/10/2018.
 */

public class WebModel {
    private int imageResource;
    private String name;
    private String url;

    public WebModel(int imageResource, String name, String url) {
        this.imageResource = imageResource;
        this.name = name;
        this.url = url;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
