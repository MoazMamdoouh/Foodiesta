package com.example.foodiesta.Model.Search;

import com.google.gson.annotations.SerializedName;

public class ListOfSearchShowListResponse {

    @SerializedName("strIngredient")
    String ingredientName ;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
