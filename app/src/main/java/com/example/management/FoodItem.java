package com.example.management;

public class FoodItem {
    public String name;
    public String date;

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public FoodItem( String name, String date) {
        this.name = name;
        this.date = date;
    }
}
