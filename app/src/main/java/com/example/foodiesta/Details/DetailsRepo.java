package com.example.foodiesta.Details;


import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class DetailsRepo {
    private MealsRemoteDataSource mealsRemoteDataSource ;
    private MealsLocalDataSource mealsLocalDataSource ;

    public DetailsRepo(MealsRemoteDataSource mealsRemoteDataSource , MealsLocalDataSource mealsLocalDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource ;
    }

    public Single getMealDetails(int id ){
        return mealsRemoteDataSource.getMealDetails(id );
    }
    public void insertMealToFavorite(int id , String mealUrl , String mealName ) {
        mealsLocalDataSource.insertFavoriteMeal(id , mealUrl , mealName);
    }

    public void insertMealToCalender(int year, int month, int day, int id, String mealImage, String mealName) {
        mealsLocalDataSource.insertMealToCalender(year , month , day , id , mealImage , mealName) ;
    }
}
