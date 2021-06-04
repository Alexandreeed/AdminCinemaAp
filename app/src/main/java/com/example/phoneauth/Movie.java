package com.example.phoneauth;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<String> time;
    private String  price;
    private String place;

    public Movie(String name, ArrayList<String> time, String  price, String place) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public void setTime(ArrayList<String> time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
