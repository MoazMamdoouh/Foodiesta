package com.example.foodiesta.Presentation.Serach;

import com.example.foodiesta.Data.Repository.Search_repo.SearchRepo;
import com.example.foodiesta.Model.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Model.Search.Ingredient.IngredientObjectResponse;

public class SearchPresenter implements SearchGateWay  {

    private SearchRepo searchRepo ;
    private SearchShowResponse searchShowResponse ;
    private SearchFragment searchFragment  ;
    public SearchPresenter(SearchShowResponse searchShowResponse , SearchFragment searchFragment) {
        this.searchRepo = new SearchRepo() ;
        this.searchShowResponse = searchShowResponse ;
        this.searchFragment = searchFragment ;
    }
    public void requestListOfCategory() {
        searchRepo.requestListOfCategories(this);
    }

    public void requestListOfIngredient() {
        searchRepo.requestListOfIngredient(this);
    }
    public void requestListOfCountries(){
        searchRepo.requestListOfCountries(this);
    }
    public void requestListOfSpacificCategory(String categoryName) {
        searchRepo.requestListOfSpacificCategory( this,categoryName);
    }

    public void requestListOfSpacificIngredient(String name) {
        searchRepo.requestListOfSpacificIngredient(this,name);
    }

    public void requestListOfSpacificCountry(String itemName) {
        searchRepo.requestListOfSpacificCountry(this,itemName);
    }

    @Override
    public void getListOfAllCategories(CategoryObjectResponse randomMealsResponse, String query) {
            searchShowResponse.onShowResponseOfRandomListByFilter(randomMealsResponse);
    }

    @Override
    public void getListOfAllIngredients(IngredientObjectResponse ingredientObjectResponse) {
        searchFragment.getAllIngredient(ingredientObjectResponse);
    }

    @Override
    public void getListOfAllCountries(CountryObjectResponse countryObjectResponse) {
        searchFragment.getAllCountries(countryObjectResponse);
    }

    @Override
    public void getListByFilter(FoodObjectResponse foodObjectResponse, String filterName) {
        if(filterName.equals("category")) {
            searchFragment.getListOfCategoriesByName(foodObjectResponse);
        }else if (filterName.equals("ingredient")){
            searchFragment.getListOfAllIngredientByName(foodObjectResponse);
        }else if (filterName.equals("country")){
            searchFragment.getListOfCategoriesByName(foodObjectResponse);
        }
    }

    @Override
    public void failureResponse(String msg) {

    }


}
