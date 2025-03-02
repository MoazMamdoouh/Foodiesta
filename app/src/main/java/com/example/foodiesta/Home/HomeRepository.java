package com.example.foodiesta.Home;


import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class HomeRepository {

    private MealsRemoteDataSource mealsRemoteDataSource ;

    public HomeRepository(MealsRemoteDataSource mealsRemoteDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
    }

    public Single getRandomMeals(){
       return  mealsRemoteDataSource.getRandomMealsResponse();
    }
    public Single getRandomDailyMeal(){
        return mealsRemoteDataSource.getRandomDailyMeal();
    }
}
