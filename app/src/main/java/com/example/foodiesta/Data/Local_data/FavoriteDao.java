package com.example.foodiesta.Data.Local_data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodiesta.Model.Calender.CalenderEntity;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertFavoriteMeal(FavoriteEntity favoriteEntity) ;

    @Query("SELECT * FROM favorite_meals_table")
    LiveData<List<FavoriteEntity>> getFavoriteMeals();

    @Delete
    void deleteMealFromFavorite(FavoriteEntity favoriteEntity) ;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertToCalender(CalenderEntity calenderEntity) ;

    @Query("SELECT * FROM calender_meals_table WHERE year=:year AND month=:month AND day=:day")
    LiveData<List<CalenderEntity>> getMealFromCalenderTable(int year, int month, int day);

    @Delete
    void deleteMealFromCalender(CalenderEntity calenderEntity) ;

}
