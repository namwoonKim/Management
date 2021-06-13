package com.example.management;

public class Recipe_information {
    public int recipe_code;
    public String name;
    public String introduce;
    public String type_code;
    public String type;
    public String food_type_code;
    public String food_type;
    public String cooking_time;
    public String calorie;
    public String amount;
    public String difficulty;
    public String material_type;
    public String price_type;

    public void setRecipe_code(int code) {
        recipe_code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFood_type_code(String food_type_code) {
        this.food_type_code = food_type_code;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public void setcooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }
}
