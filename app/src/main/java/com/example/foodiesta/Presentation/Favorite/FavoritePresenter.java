package com.example.foodiesta.Presentation.Favorite;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodiesta.Data.Repository.Favorite_repo.FavoriteRepo;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter {

    private FavoriteRepo favoriteRepo ;
    private FavoriteFragment favoriteFragment ;

    public FavoritePresenter(FavoriteRepo favoriteRepo , FavoriteFragment favoriteFragment) {
        this.favoriteRepo = favoriteRepo;
        this.favoriteFragment = favoriteFragment ;
    }

    @SuppressLint("CheckResult")
    public void requestFavoriteMeals(){
        Flowable<List<FavoriteEntity>> listFlowable = favoriteRepo.getFavoriteMeals() ;
        listFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> favoriteFragment.getListOfFavoriteMeals(response),
                        throwable -> Log.e("DBError", "Failed to fetch meals", throwable)
                );
    }

    public void deleteMealFromFavorite(int id, String url, String name) {
        favoriteRepo.deleteMealFromFavorite(id , url , name);
    }
}
