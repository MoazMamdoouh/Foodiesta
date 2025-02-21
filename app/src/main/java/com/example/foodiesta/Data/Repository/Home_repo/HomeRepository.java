package com.example.foodiesta.Data.Repository.Home_repo;


import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Utilities.OnResponseSend;

public class HomeRepository {

    private MealsRemoteDataSource mealsRemoteDataSource ;

    public HomeRepository(MealsRemoteDataSource mealsRemoteDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
    }

    public void getAllRemoteMeals(OnResponseSend onResponseSend){
        mealsRemoteDataSource.getRandomMealsResponse(onResponseSend);
    }
    public void getRandomMeal(OnResponseSend onResponseSend){
        mealsRemoteDataSource.getRandomDailyMeal(onResponseSend);
    }
}
