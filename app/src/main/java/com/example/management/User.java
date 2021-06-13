package com.example.management;

public class User {

    public String id;  // 사용자 id
    public String name; // 사용자명
    public String amount; // 1회당 제공량
    public String kcal; // 칼로리
    public String protein; // 단백질
    public String fat; // 지방
    public String carb; // 탄수화물

    // TODO : get,set 함수 생략
    public void setId(String id) { this.id = id; }
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setKcal(String kcal) { this.kcal = kcal; }
    public void setProtein(String protein) {
        this.protein = protein;
    }
    public void setFat(String fat) {
        this.fat = fat;
    }
    public void setCarb(String carb) {
        this.carb = carb;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getAmount() {return amount;}
    public String getKcal() {return kcal;}
    public String getProtein() {return protein;}
    public String getFat() {return fat;}
    public String getCarb() {return carb;}

}
