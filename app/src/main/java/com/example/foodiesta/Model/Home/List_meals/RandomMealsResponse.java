package com.example.foodiesta.Model.Home.List_meals;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RandomMealsResponse {

    @SerializedName("meals")
    private List<ListOfRandomMeals> listOfRandomMeals = new ArrayList<>();

    public List<ListOfRandomMeals> getListOfRandomMeals() {
        return listOfRandomMeals;
    }

    public void setListOfRandomMeals(List<ListOfRandomMeals> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }
}
