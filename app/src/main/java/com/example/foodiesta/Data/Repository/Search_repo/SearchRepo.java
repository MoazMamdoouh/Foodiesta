package com.example.foodiesta.Data.Repository.Search_repo;

import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Presentation.Serach.SearchGateWay;

public class SearchRepo  {

    private MealsRemoteDataSource mealsRemoteDataSource ;
    public SearchRepo() {
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance() ;
    }

    public void requestListOfCategories(SearchGateWay searchRepoGateWay) {
       mealsRemoteDataSource.getAllCategories(searchRepoGateWay);
    }

    public void requestListOfSpacificCategory(SearchGateWay searchGateWay , String categoryName) {
        mealsRemoteDataSource.getListOfSpacificCategory(searchGateWay,categoryName);
    }

    public void requestListOfIngredient(SearchGateWay searchGateWay) {
        mealsRemoteDataSource.getListOfAllIngredients(searchGateWay);
    }

    public void requestListOfSpacificIngredient(SearchGateWay searchGateWay,String name) {
        mealsRemoteDataSource.getListOfSpacificIngredient(searchGateWay,name);
    }

    public void requestListOfCountries(SearchGateWay searchGateWay) {
        mealsRemoteDataSource.getListOfCountries(searchGateWay);
    }

    public void requestListOfSpacificCountry(SearchGateWay searchGateWay,String itemName) {
        mealsRemoteDataSource.getListOfSpacificCountry(searchGateWay , itemName);
    }
}
