package com.example.foodiesta.Search;

import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class SearchRepo  {

    private MealsRemoteDataSource mealsRemoteDataSource ;
    public SearchRepo() {
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance() ;
    }

    public Single requestListOfCategories() {
       return mealsRemoteDataSource.getAllCategories();
    }

    public Single requestListOfSpacificCategory( String categoryName) {
       return  mealsRemoteDataSource.getListOfSpacificCategory(categoryName);
    }

    public Single requestListOfIngredient() {
        return mealsRemoteDataSource.getListOfAllIngredients();
    }

    public Single requestListOfSpacificIngredient(String name) {
       return  mealsRemoteDataSource.getListOfSpacificIngredient(name);
    }

    public Single requestListOfCountries() {
        return mealsRemoteDataSource.getListOfCountries();
    }

    public Single requestListOfSpacificCountry(String itemName) {
       return  mealsRemoteDataSource.getListOfSpacificCountry( itemName);
    }
}
