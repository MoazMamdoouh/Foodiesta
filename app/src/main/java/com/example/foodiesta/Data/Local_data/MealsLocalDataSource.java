package com.example.foodiesta.Data.Local_data;

import android.content.Context;

import com.example.foodiesta.Model.Favorite.FavoriteEntity;

public class MealsLocalDataSource {

    private FavoriteDao favoriteDao ;
    private static MealsLocalDataSource instance = null ;
    private Context context ;

    public MealsLocalDataSource(Context context) {
        this.context = context ;

    }


    public void insertFavoriteMeal(int mealId , String mealUrl , String mealName){
        favoriteDao = FavoriteDataBase.getInstance(context).favoriteDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                FavoriteEntity favoriteEntity = new FavoriteEntity(mealId , mealUrl , mealName) ;
                favoriteDao.insertFavoriteMeal(favoriteEntity);
            }
        }).start();


    }

}
