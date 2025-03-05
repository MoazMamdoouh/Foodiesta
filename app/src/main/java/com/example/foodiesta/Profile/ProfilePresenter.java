package com.example.foodiesta.Profile;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter implements OnFireStoreResponse {

    private ProfileRepo profileRepo ;
    private ProfileFragment profileFragment ;

    public ProfilePresenter(ProfileRepo profileRepo , ProfileFragment profileFragment) {
        this.profileRepo = profileRepo;
        this.profileFragment = profileFragment ;
    }

    @SuppressLint("CheckResult")
    void getListOfFavoriteMeal(String userId , FirebaseFirestore firebaseFirestore){
        Flowable<List<FavoriteEntity>> flowable = profileRepo.getListOfFavoriteMeals() ;
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> profileRepo.insertFavoriteMealsInServer(userId , firebaseFirestore
                                 , response , this )
                );
    }


    public void deleteAllFavoriteMealsFromRoom() {
        profileRepo.deleteAllFavoriteMealsFromRoom();
    }

    public void downloadAllFavoriteMeals(String userID , FirebaseFirestore firestore) {
        profileRepo.downLoadFavoriteMeals(userID , firestore , this);
    }

    @Override
    public void successInsertionToServer(String success) {
           profileFragment.successInsertFavorite(success);
    }

    @Override
    public void successDownload(List listedDownload , String type) {
        profileFragment.successDownLoadFavorite(listedDownload , type);

    }

    @Override
    public void failedInsertionToServer(String success) {
    }

    public <T> void inseMealsList(List<T> downloadedList , String type) {
        profileRepo.insertFavoriteMeals(downloadedList , type);
    }

    @SuppressLint("CheckResult")
    public void insertCalenderMealsToServer(FirebaseFirestore firebaseFirestore, String userID) {
        Flowable<List<CalenderEntity>> flowable = profileRepo.getListOfCalenderMeals();
        flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        response ->  profileRepo.insertCalenderMealsToServer(response , firebaseFirestore , userID ,this)
                                );
    }

    public void deleteAllCalenderMealsFromRoom() {
        profileRepo.deleteAllCalenderMealsFromRoom();
    }

    public void downLoadAllCalenderMeals(String userId, FirebaseFirestore firebaseFirestore) {
        profileRepo.downLoadAllCalenderMeals(userId , firebaseFirestore , this);
    }
}
