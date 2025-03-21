package com.example.foodiesta.Data.Local_data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertFavoriteMeal(FavoriteEntity favoriteEntity) ;

    @Query("SELECT * FROM favorite_meals_table")
    Flowable<List<FavoriteEntity>> getFavoriteMeals();

    @Delete
    Completable deleteMealFromFavorite(FavoriteEntity favoriteEntity) ;

    @Query("DELETE FROM favorite_meals_table")
    Completable deleteAllFavoriteMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertListOfFavoriteMeals(List<FavoriteEntity> favoriteEntities) ;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertToCalender(CalenderEntity calenderEntity) ;

    @Query("SELECT * FROM calender_meals_table WHERE year=:year AND month=:month AND day=:day")
    Flowable<List<CalenderEntity>> getMealFromCalenderTable(int year, int month, int day);

    @Delete
    Completable deleteMealFromCalender(CalenderEntity calenderEntity) ;

    @Query("SELECT * FROM calender_meals_table")
    Flowable<List<CalenderEntity>> getAllCalenderMeals();
    @Query("DELETE FROM calender_meals_table")
    Completable deleteAllFromCalender();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertListOfCalenderMeals(List<CalenderEntity> calenderEntities) ;
}
