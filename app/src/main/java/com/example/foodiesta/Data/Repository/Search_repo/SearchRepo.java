package com.example.foodiesta.Data.Repository.Search_repo;

import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;
import com.example.foodiesta.Presentation.Serach.SearchGateWay;
import com.example.foodiesta.Utilities.OnResponseSend;

public class SearchRepo implements OnResponseSend {

    private SearchGateWay searchGateWay ;
    private MealsRemoteDataSource mealsRemoteDataSource ;
    public SearchRepo(SearchGateWay searchGateWay) {
        this.searchGateWay = searchGateWay ;
        mealsRemoteDataSource = MealsRemoteDataSource.getInstance() ;
    }

    public void getListOfIngredient(){
        mealsRemoteDataSource.getRandomMealsResponse(this);
    }

    @Override
    public void success(RandomMealsResponse randomMealsResponse) {
        searchGateWay.getListOfIngredients(randomMealsResponse);
    }

    @Override
    public void failed(String msg) {

    }

    @Override
    public void dailyMealSuccess(RandomDailyMealResponse randomDailyMealResponse) {

    }
}
