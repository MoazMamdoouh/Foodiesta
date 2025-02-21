package com.example.foodiesta.Presentation.Home;

import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;

public interface HomeGateWay {
     void showMeals(RandomMealsResponse randomMealsResponse);
     void showRandomMeal(RandomDailyMealResponse randomDailyMealResponse);
     void showError(String msg) ;
}
