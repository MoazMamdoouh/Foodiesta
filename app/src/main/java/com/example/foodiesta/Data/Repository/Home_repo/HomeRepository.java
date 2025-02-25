package com.example.foodiesta.Data.Repository.Home_repo;


import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Utilities.OnFoodObjectResponse;

public class HomeRepository {

    private MealsRemoteDataSource mealsRemoteDataSource ;

    public HomeRepository(MealsRemoteDataSource mealsRemoteDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
    }

    public void getAllRemoteMeals(OnFoodObjectResponse onFoodObjectResponse){
        mealsRemoteDataSource.getRandomMealsResponse(onFoodObjectResponse);
    }
    public void getRandomMeal(OnFoodObjectResponse onFoodObjectResponse){
        mealsRemoteDataSource.getRandomDailyMeal(onFoodObjectResponse);
    }
}
