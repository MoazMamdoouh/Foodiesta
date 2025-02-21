package com.example.foodiesta.Presentation.Home;

import com.example.foodiesta.Data.Repository.Home_repo.HomeRepository;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;
import com.example.foodiesta.Utilities.OnResponseSend;

public class HomePresenter implements OnResponseSend {

    private HomeRepository repository ;
    private HomeGateWay homeGateWay ;

    public HomePresenter(HomeRepository repository , HomeGateWay homeGateWay) {
        this.repository = repository;
        this.homeGateWay =  homeGateWay;
    }

    public void getAllRemoteMeals(){
        //return Random meal Response
        repository.getAllRemoteMeals(this) ;
    }
    public void getRandomMeal(){
        repository.getRandomMeal(this) ;
    }

    @Override
    public void success(RandomMealsResponse randomMealsResponse) {
        homeGateWay.showMeals(randomMealsResponse);
    }

    @Override
    public void failed(String msg) {
        homeGateWay.showError(msg);
    }

    @Override
    public void dailyMealSuccess(RandomDailyMealResponse randomDailyMealResponse) {
        homeGateWay.showRandomMeal(randomDailyMealResponse);
    }
}
