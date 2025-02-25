package com.example.foodiesta.Model.Search.Country;

import com.google.gson.annotations.SerializedName;

public class ListOfCountriesSearch {

    @SerializedName("strArea")
    private String countryName ;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
