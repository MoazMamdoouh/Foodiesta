package com.example.foodiesta.Data.Local_data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;

@Database(entities = {FavoriteEntity.class , CalenderEntity.class} , version = 1)
public abstract class FavoriteDataBase extends RoomDatabase {

    private static FavoriteDataBase instance = null ;
    public abstract FavoriteDao favoriteDao() ;
    public static FavoriteDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,FavoriteDataBase.class , "favorite_bd").build();
        }
        return instance ;
    }

}
