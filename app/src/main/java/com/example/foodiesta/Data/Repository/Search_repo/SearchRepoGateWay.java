package com.example.foodiesta.Data.Repository.Search_repo;

import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;

public interface SearchRepoGateWay {

    void getRandomListByFilter(RandomMealsResponse randomMealsResponse , String query) ;

    void getErrorMsg(String msg) ;

}
