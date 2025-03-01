package com.example.foodiesta.Presentation.Serach;

import android.annotation.SuppressLint;

import com.example.foodiesta.Data.Repository.Search_repo.SearchRepo;
import com.example.foodiesta.Model.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Model.Search.Ingredient.IngredientObjectResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter  {

    private SearchRepo searchRepo ;
    private SearchShowResponse searchShowResponse ;
    private SearchFragment searchFragment  ;
    public SearchPresenter(SearchShowResponse searchShowResponse , SearchFragment searchFragment) {
        this.searchRepo = new SearchRepo() ;
        this.searchShowResponse = searchShowResponse ;
        this.searchFragment = searchFragment ;
    }
    @SuppressLint("CheckResult")
    public void requestListOfCategory() {
        Single<CategoryObjectResponse> observable =  searchRepo.requestListOfCategories();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response ->  searchShowResponse.onShowResponseOfRandomListByFilter(response)
                );
    }

    @SuppressLint("CheckResult")
    public void requestListOfIngredient() {
       Single<IngredientObjectResponse> observable =  searchRepo.requestListOfIngredient();
       observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                     downStream ->  searchFragment.getAllIngredient(downStream)
               );
    }
    @SuppressLint("CheckResult")
    public void requestListOfCountries(){
       Single<CountryObjectResponse> observable =  searchRepo.requestListOfCountries();
       observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       response ->  searchFragment.getAllCountries(response)
               );
    }
    @SuppressLint("CheckResult")
    public void requestListOfSpacificCategory(String categoryName ) {
       Single<FoodObjectResponse> observer =searchRepo.requestListOfSpacificCategory(categoryName);
       observer.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       response ->  searchFragment.getListOfCategoriesByName(response)
               );
    }

    @SuppressLint("CheckResult")
    public void requestListOfSpacificIngredient(String name) {
        Single<FoodObjectResponse> observable = searchRepo.requestListOfSpacificIngredient(name);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> searchFragment.getListOfAllIngredientByName(response)
                );
    }

    @SuppressLint("CheckResult")
    public void requestListOfSpacificCountry(String itemName) {
        Single<FoodObjectResponse> observable =  searchRepo.requestListOfSpacificCountry(itemName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response ->  searchFragment.getListOfCountriesByName(response)
                );
    }


}
