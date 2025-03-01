package com.example.foodiesta.Presentation.Profile;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodiesta.Data.Repository.ProfileRepo;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter {

    private ProfileRepo profileRepo ;
    private ProfileFragment profileFragment ;

    public ProfilePresenter(ProfileRepo profileRepo , ProfileFragment profileFragment) {
        this.profileRepo = profileRepo;
        this.profileFragment = profileFragment ;
    }

    @SuppressLint("CheckResult")
    void getListOfFavoriteMeal(String userId , FirebaseFirestore firebaseFirestore
            , FirebaseAuth firebaseAuth ){
        Flowable<List<FavoriteEntity>> flowable = profileRepo.getListOfFavoriteMeals() ;
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> insertFavoriteMealsToFireStore(userId , firebaseFirestore
                                ,firebaseAuth , response )
                );
    }

    void insertFavoriteMealsToFireStore(String userId , FirebaseFirestore firebaseFirestore
            , FirebaseAuth firebaseAuth , List<FavoriteEntity> favoriteEntities){

        for (FavoriteEntity meal : favoriteEntities) {
            firebaseFirestore.collection("users_backUp")
                    .document(userId)
                    .collection("favorite_meals")
                    .document(String.valueOf(meal.getMealId()))
                    .set(meal)
                    .addOnSuccessListener(aVoid -> profileFragment.successInsert())
                    .addOnFailureListener(e -> Log.e("FirestoreError", "Backup failed", e));
        }
    }
}
