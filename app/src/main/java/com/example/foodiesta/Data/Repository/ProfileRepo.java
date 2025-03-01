package com.example.foodiesta.Data.Repository;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;

import io.reactivex.rxjava3.core.Flowable;

public class ProfileRepo {
    private MealsLocalDataSource mealsLocalDataSource ;

    public ProfileRepo(MealsLocalDataSource mealsLocalDataSource) {
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public Flowable getListOfFavoriteMeals(){
        return mealsLocalDataSource.getFavoriteMeals();
    }
}
