package com.example.foodiesta.Search.Country;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryObjectResponse {
    @SerializedName("meals")
    private List<ListOfCountriesSearch> listOfCountriesSearches;

    public List<ListOfCountriesSearch> getListOfCountriesSearches() {
        return listOfCountriesSearches;
    }

    public void setListOfCountriesSearches(List<ListOfCountriesSearch> listOfCountriesSearches) {
        this.listOfCountriesSearches = listOfCountriesSearches;
    }
}
