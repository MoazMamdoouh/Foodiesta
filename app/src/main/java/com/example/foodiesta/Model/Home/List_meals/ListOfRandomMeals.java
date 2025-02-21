package com.example.foodiesta.Model.Home.List_meals;

import com.google.gson.annotations.SerializedName;

public class ListOfRandomMeals {

    @SerializedName("idMeal")
    private int id ;
    @SerializedName("strMeal")
    private String mealName ;
    @SerializedName("strMealThumb")
    private String mealImage ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }
}
