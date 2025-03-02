package com.example.foodiesta.Search.Category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryObjectResponse {
    @SerializedName("categories")
    List<ListOfCategoriesSearch> listOfCategoriesSearches ;

    public List<ListOfCategoriesSearch> getListOfCategoriesSearches() {
        return listOfCategoriesSearches;
    }

    public void setListOfCategoriesSearches(List<ListOfCategoriesSearch> listOfCategoriesSearches) {
        this.listOfCategoriesSearches = listOfCategoriesSearches;
    }
}
