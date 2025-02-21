package com.example.foodiesta.Utilities;

import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;

public interface OnResponseSend <T>{
    public void success(RandomMealsResponse randomMealsResponse);
    public void failed(String msg) ;
    public void dailyMealSuccess(RandomDailyMealResponse randomDailyMealResponse) ;
}
