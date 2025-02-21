package com.example.foodiesta.Data.Remore_data;

import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("filter.php")
    Call<RandomMealsResponse> getListOfMeals(@Query("i") String mealName) ;

    @GET("random.php")
    Call<RandomDailyMealResponse> getRandomDailyMeal();

    @GET("lookup.php")
    Call<DetailsResponse> getMealDetails(@Query("i") int id) ;




}
