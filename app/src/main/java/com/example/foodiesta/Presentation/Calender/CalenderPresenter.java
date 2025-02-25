package com.example.foodiesta.Presentation.Calender;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Data.Repository.Calender.CalenderRepo;
import com.example.foodiesta.Model.Calender.CalenderEntity;

import java.util.List;

public class CalenderPresenter {

    private CalenderRepo calenderRepo ;

    public CalenderPresenter(CalenderRepo calenderRepo) {
        this.calenderRepo = calenderRepo;
    }

    LiveData<List<CalenderEntity>> getMealThroughSpacificDate(int year , int month , int day ){
       return  calenderRepo.getMealThroughSpacificDate(year , month , day );
    }

    public void deleteMealFromCalender(int id , int year , int month , int day , String mealImage , String mealName) {
        calenderRepo.deleteMealFromCalender(id ,year , month , day , mealImage , mealName);
    }
}
