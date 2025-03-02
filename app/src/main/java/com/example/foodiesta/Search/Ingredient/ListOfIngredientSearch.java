package com.example.foodiesta.Search.Ingredient;

import com.google.gson.annotations.SerializedName;

public class ListOfIngredientSearch {

    @SerializedName("idIngredient")
    private int ingredientId ;
    @SerializedName("strIngredient")
    private String ingredientName ;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
