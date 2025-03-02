package com.example.foodiesta.Details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsResponse {
    @SerializedName("meals")
    private List<DetailsMealResponse> listOfRandomMeals ;

    public List<DetailsMealResponse> getListOfRandomMeals() {
        return listOfRandomMeals;
    }

    public void setListOfRandomMeals(List<DetailsMealResponse> listOfRandomMeals) {
        this.listOfRandomMeals = listOfRandomMeals;
    }
}
