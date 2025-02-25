package com.example.foodiesta.Presentation.Home;

import com.example.foodiesta.Utilities.FoodObjectResponse;

public interface HomeGateWay {
     void showMeals(FoodObjectResponse foodObjectResponse);
     void showRandomMeal(FoodObjectResponse foodObjectResponse);
     void showError(String msg) ;
}
