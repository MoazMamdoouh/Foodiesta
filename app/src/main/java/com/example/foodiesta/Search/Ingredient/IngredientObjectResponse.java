package com.example.foodiesta.Search.Ingredient;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientObjectResponse {
    @SerializedName("meals")
    private List<ListOfIngredientSearch> allIngredientsList ;

    public List<ListOfIngredientSearch> getAllIngredientsList() {
        return allIngredientsList;
    }

    public void setAllIngredientsList(List<ListOfIngredientSearch> allIngredientsList) {
        this.allIngredientsList = allIngredientsList;
    }
}
