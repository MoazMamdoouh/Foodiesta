package com.example.foodiesta.Model.Home.Random_meal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomDailyMealResponse {
    @SerializedName("meals")
    private List<SingleRandomMeal> listOfRandomMeals ;

    public List<SingleRandomMeal> getListOfRandomMeals() {
        return listOfRandomMeals;
    }

    public void setListOfRandomMeals(List<SingleRandomMeal> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }
}
