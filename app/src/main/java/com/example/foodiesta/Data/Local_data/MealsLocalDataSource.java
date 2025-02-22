package com.example.foodiesta.Data.Local_data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

public class MealsLocalDataSource {

    private FavoriteDao favoriteDao ;
    private static MealsLocalDataSource instance = null ;
    private Context context ;

    public MealsLocalDataSource(Context context) {
        this.context = context ;
        favoriteDao = FavoriteDataBase.getInstance(context).favoriteDao();
    }


    public void insertFavoriteMeal(int mealId , String mealUrl , String mealName){

        new Thread(new Runnable() {
            @Override
            public void run() {
                FavoriteEntity favoriteEntity = new FavoriteEntity(mealId , mealUrl , mealName) ;
                favoriteDao.insertFavoriteMeal(favoriteEntity);
            }
        }).start();
    }
    public LiveData<List<FavoriteEntity>> getFavoriteMeals(){
        return favoriteDao.getFavoriteMeals() ;
    }

    public void deleteMealFromFavorite(int id, String url, String name) {
        FavoriteEntity favoriteEntity = new FavoriteEntity(id , url , name );
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteDao.deleteMealFromFavorite(favoriteEntity);
            }
        }).start();

    }
}
