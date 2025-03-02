package com.example.foodiesta.Calender;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Calender.Calender.CalenderEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class CalenderRepo {

    private MealsLocalDataSource mealsLocalDataSource ;

    public CalenderRepo(MealsLocalDataSource mealsLocalDataSource) {
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public Flowable<List<CalenderEntity>> getMealThroughSpacificDate(int year , int month , int day ){
       return  mealsLocalDataSource.getMealFromCalender(year , month , day);
    }

    public void deleteMealFromCalender(int id , int year , int month , int day , String mealImage , String mealName) {
        mealsLocalDataSource.deleteMealFromCalender(id ,year , month , day , mealImage , mealName);
    }
}
