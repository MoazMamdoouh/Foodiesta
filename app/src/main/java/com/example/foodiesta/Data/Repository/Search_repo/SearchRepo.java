package com.example.foodiesta.Data.Repository.Search_repo;

import android.util.Log;

import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Presentation.Serach.SearchGateWay;

public class SearchRepo implements SearchRepoGateWay {

    private SearchGateWay searchGateWay ;
    private MealsRemoteDataSource mealsRemoteDataSource ;
    public SearchRepo(SearchGateWay searchGateWay) {
        this.searchGateWay = searchGateWay ;
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance() ;
    }

    public void requestListOfIngredient(){
        mealsRemoteDataSource.getListOfIngredient(this);
    }

    public void requestListOfCountries(){
        mealsRemoteDataSource.getListOfCountries(this);
    }

    public void requestRandomListOfCategory(String category){
        mealsRemoteDataSource.getListOfCategories(this , category);
    }

    @Override
    public void getRandomListByFilter(RandomMealsResponse randomMealsResponse , String query) {
        if(query.equals("ingredient")){
            Log.i("TAG", "getRandomListByFilter: ingredient ");
            searchGateWay.getRandomListByFilterResponseSuccess(randomMealsResponse , query);
        }
        else if (query.equals("countries")) {
            Log.i("TAG", "getRandomListByFilter: countries");
            searchGateWay.getRandomListByFilterResponseSuccess(randomMealsResponse , query);
        }
        else if (query.equals("category")) {
            Log.i("TAG", "getRandomListByFilter: category ");
            searchGateWay.getRandomListByFilterResponseSuccess(randomMealsResponse , query);
        }
    }


    @Override
    public void getErrorMsg(String msg) {
        searchGateWay.failureResponse(msg);
    }
}
