package com.example.foodiesta.Calender;

import android.annotation.SuppressLint;

import com.example.foodiesta.Calender.Calender.CalenderEntity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPresenter {

    private CalenderRepo calenderRepo ;
    private CalenderFragment calenderFragment ;

    public CalenderPresenter(CalenderRepo calenderRepo , CalenderFragment calenderFragment) {
        this.calenderRepo = calenderRepo;
        this.calenderFragment = calenderFragment ;
    }

    @SuppressLint("CheckResult")
    void getMealThroughSpacificDate(int year , int month , int day ){
       Flowable<List<CalenderEntity>> flowable =  calenderRepo.getMealThroughSpacificDate(year , month , day );
       flowable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       response -> calenderFragment.getMealsThroughSpacificDate(response)
               );
    }

    public void deleteMealFromCalender(int id , int year , int month , int day , String mealImage , String mealName) {
        calenderRepo.deleteMealFromCalender(id ,year , month , day , mealImage , mealName);
    }
}
