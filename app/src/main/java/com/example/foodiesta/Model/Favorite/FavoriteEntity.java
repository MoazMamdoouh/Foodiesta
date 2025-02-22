package com.example.foodiesta.Model.Favorite;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_meals_table")
public class FavoriteEntity {
    @PrimaryKey
    int mealId ;
    String mealUrl ;
    String mealName ;

    public FavoriteEntity(int mealId, String mealUrl, String mealName) {
        this.mealId = mealId;
        this.mealUrl = mealUrl;
        this.mealName = mealName;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealUrl() {
        return mealUrl;
    }

    public void setMealUrl(String mealUrl) {
        this.mealUrl = mealUrl;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
