package com.example.foodiesta.Model.Calender;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Calender_meals_table")
public class CalenderEntity {
    @PrimaryKey
    private int mealId ;
    private int year ;
    private int month ;
    private int day ;
    private String mealImage ;
    private String mealName ;

    public CalenderEntity(int mealId, int year, int month, int day, String mealImage, String mealName) {
        this.mealId = mealId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.mealImage = mealImage;
        this.mealName = mealName;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
