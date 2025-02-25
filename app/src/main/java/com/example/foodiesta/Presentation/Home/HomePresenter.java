package com.example.foodiesta.Presentation.Home;

import com.example.foodiesta.Data.Repository.Home_repo.HomeRepository;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Utilities.OnFoodObjectResponse;

public class HomePresenter implements OnFoodObjectResponse {

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
    public void success(FoodObjectResponse foodObjectResponse) {
        homeGateWay.showMeals(foodObjectResponse);
    }

    @Override
    public void successGetDailyRandomMeal(FoodObjectResponse foodObjectResponse) {
        homeGateWay.showRandomMeal(foodObjectResponse);
    }

    @Override
    public void failed(String msg) {
        homeGateWay.showError(msg);
    }
}
