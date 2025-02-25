package com.example.foodiesta.Data.Local_data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Model.Calender.CalenderEntity;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

public class MealsLocalDataSource {

    private FavoriteDao favoriteDao ;
    private static MealsLocalDataSource instance = null ;
    private Context context ;

    public MealsLocalDataSource(Context context) {
        favoriteDao = FavoriteDataBase.getInstance(context).favoriteDao();
        this.context = context ;
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

    public void insertMealToCalender(int year, int month, int day, int id, String mealImage, String mealName) {
        CalenderEntity calenderEntity = new CalenderEntity(id , year , month , day , mealImage , mealName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteDao.insertToCalender(calenderEntity);
            }
        }).start();
    }

    public LiveData<List<CalenderEntity>> getMealFromCalender(int year , int month , int day){
        return favoriteDao.getMealFromCalenderTable(year , month , day) ;
    }

}
