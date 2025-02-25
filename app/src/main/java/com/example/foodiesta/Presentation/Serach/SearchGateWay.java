package com.example.foodiesta.Presentation.Serach;

import com.example.foodiesta.Model.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Model.Search.Ingredient.IngredientObjectResponse;

public interface SearchGateWay {

    void getListOfAllCategories(CategoryObjectResponse randomMealsResponse , String query) ;
    void getListOfAllIngredients(IngredientObjectResponse ingredientObjectResponse) ;
    void getListOfAllCountries(CountryObjectResponse countryObjectResponse) ;
    void getListByFilter(FoodObjectResponse foodObjectResponse, String filterName) ;
    void failureResponse(String msg) ;
}
