package com.example.foodiesta.Presentation.Serach;

import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;

public interface SearchGateWay {

    void getRandomListByFilterResponseSuccess(RandomMealsResponse randomMealsResponse , String query) ;

    void failureResponse(String msg) ;
}
