package com.example.management;

public class Recipe_material {
    public int recipe_code;
    public int order;
    public String material_name;
    public String material_capacity;
    public String material_type_code;
    public String material_type;

    public void setRecipe_code(int code) {
        recipe_code = code;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public void setMaterial_capacity(String material_capacity) {
        this.material_capacity = material_capacity;
    }

    public void setMaterial_type_code(String material_type_code) {
        this.material_type_code = material_type_code;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }
}
