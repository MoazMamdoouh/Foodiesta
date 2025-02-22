package com.example.foodiesta.Data.Repository.Favorite_repo;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

public class FavoriteRepo {

    private MealsLocalDataSource mealsLocalDataSource ;

    public FavoriteRepo(MealsLocalDataSource mealsLocalDataSource) {
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public LiveData<List<FavoriteEntity>> getFavoriteMeals(){
        return mealsLocalDataSource.getFavoriteMeals() ;
    }

    public void deleteMealFromFavorite(int id, String url, String name) {
        mealsLocalDataSource.deleteMealFromFavorite(id , url , name);
    }
}
