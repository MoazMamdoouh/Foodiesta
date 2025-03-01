package com.example.foodiesta.Data.Local_data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Model.Calender.CalenderEntity;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDataSource {
    private FavoriteDao favoriteDao ;
    private static MealsLocalDataSource instance = null ;
    private Context context ;

    public MealsLocalDataSource(Context context) {
        favoriteDao = FavoriteDataBase.getInstance(context).favoriteDao();
        this.context = context ;
    }
    public void insertFavoriteMeal(int mealId , String mealUrl , String mealName){
                FavoriteEntity favoriteEntity = new FavoriteEntity(mealId , mealUrl , mealName) ;
                favoriteDao.insertFavoriteMeal(favoriteEntity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
    }
    public Flowable<List<FavoriteEntity>> getFavoriteMeals(){
        return favoriteDao.getFavoriteMeals() ;
    }
    public void deleteMealFromFavorite(int id, String url, String name) {
        FavoriteEntity favoriteEntity = new FavoriteEntity(id , url , name );
        favoriteDao.deleteMealFromFavorite(favoriteEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void insertMealToCalender(int year, int month, int day, int id, String mealImage, String mealName) {
        CalenderEntity calenderEntity = new CalenderEntity(id , year , month , day , mealImage , mealName);
        favoriteDao.insertToCalender(calenderEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public Flowable<List<CalenderEntity>> getMealFromCalender(int year , int month , int day){
        return favoriteDao.getMealFromCalenderTable(year , month , day) ;
    }
    public void deleteMealFromCalender(int id , int year , int month , int day , String mealImage , String mealName) {
        CalenderEntity calenderEntity = new CalenderEntity(id , year,month,day,mealImage,mealName);
        favoriteDao.deleteMealFromCalender(calenderEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteAllFavoriteMealsFromRoom() {
        favoriteDao.deleteAllFavoriteMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public <T> void insertFavoriteMeals(List<T> downloadedList , String type) {
        if (type.equals("favorite")) {
            List<FavoriteEntity> favoriteEntities = new ArrayList<>();
            for (T item : downloadedList) {
                if (item instanceof FavoriteEntity) {
                    favoriteEntities.add((FavoriteEntity) item);
                }
            }
            favoriteDao.insertListOfFavoriteMeals(favoriteEntities).subscribeOn(Schedulers.io())
                    .subscribe();
        }else{
            List<CalenderEntity> calenderEntities = new ArrayList<>();
            for (T item : downloadedList) {
                if (item instanceof CalenderEntity) {
                    calenderEntities.add((CalenderEntity) item);
                }
            }
            favoriteDao.insertListOfCalenderMeals(calenderEntities).subscribeOn(Schedulers.io())
                    .subscribe();
        }
    }

    public Flowable<List<CalenderEntity>> getAllCalenderMeals() {
        return favoriteDao.getAllCalenderMeals();
    }

    public void deleteAllCalenderMealsFromRoom() {
        favoriteDao.deleteAllFromCalender().subscribeOn(Schedulers.io())
                .subscribe();
    }
}
