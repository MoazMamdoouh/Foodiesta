package com.example.foodiesta.Profile;

import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;
import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class ProfileRepo {
    private MealsLocalDataSource mealsLocalDataSource ;
    private MealsRemoteFireBase mealsRemoteFireBase;

    public ProfileRepo(MealsLocalDataSource mealsLocalDataSource , MealsRemoteFireBase mealsRemoteFireBase) {
        this.mealsLocalDataSource = mealsLocalDataSource;
        this.mealsRemoteFireBase = mealsRemoteFireBase;
    }

    public Flowable getListOfFavoriteMeals(){
        return mealsLocalDataSource.getFavoriteMeals();
    }

    public void deleteAllFavoriteMealsFromRoom() {
        mealsLocalDataSource.deleteAllFavoriteMealsFromRoom();
    }

    public void insertFavoriteMealsInServer(String userId, FirebaseFirestore firebaseFirestore
            , List<FavoriteEntity> response , OnFireStoreResponse fireStoreResponse) {
        mealsRemoteFireBase.insertFavoriteMealsToServer(userId , firebaseFirestore , response , fireStoreResponse) ;
    }

    public void downLoadFavoriteMeals(String userId , FirebaseFirestore firebaseFirestore , OnFireStoreResponse onFireStoreResponse) {
        mealsRemoteFireBase.downloadFavoriteMeals(userId , firebaseFirestore , onFireStoreResponse);
    }

    public <T> void insertFavoriteMeals(List<T> downloadedList ,  String type) {
        mealsLocalDataSource.insertFavoriteMeals(downloadedList , type);
    }

    public void insertCalenderMealsToServer(List<CalenderEntity> calenderEntities , FirebaseFirestore firebaseFirestore
            , String userID , OnFireStoreResponse onFireStoreResponse) {
        mealsRemoteFireBase.insertCalenderMealsToServer(calenderEntities , firebaseFirestore , userID , onFireStoreResponse);
    }

    public Flowable<List<CalenderEntity>> getListOfCalenderMeals() {
        return mealsLocalDataSource.getAllCalenderMeals();
    }

    public void deleteAllCalenderMealsFromRoom() {
        mealsLocalDataSource.deleteAllCalenderMealsFromRoom();
    }

    public void downLoadAllCalenderMeals(String userId, FirebaseFirestore firebaseFirestore, OnFireStoreResponse onFireStoreResponse) {
            mealsRemoteFireBase.downLoadAllCalenderMeals(userId , firebaseFirestore , onFireStoreResponse);
    }
}
