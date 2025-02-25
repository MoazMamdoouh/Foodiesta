package com.example.foodiesta.Utilities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodObjectResponse {

    @SerializedName("meals")
    private List<ListOfFoodResponse> listOfRandomMeals = new ArrayList<>();

    public List<ListOfFoodResponse> getListOfRandomMeals() {
        return listOfRandomMeals;
    }

    public void setListOfRandomMeals(List<ListOfFoodResponse> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }
}
