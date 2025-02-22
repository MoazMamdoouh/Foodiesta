package com.example.foodiesta.Data.Local_data;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.foodiesta.Model.Favorite.FavoriteEntity;
@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertFavoriteMeal(FavoriteEntity favoriteEntity) ;
}
