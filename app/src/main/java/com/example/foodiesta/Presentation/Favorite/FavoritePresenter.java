package com.example.foodiesta.Presentation.Favorite;

import androidx.lifecycle.LiveData;

import com.example.foodiesta.Data.Repository.Favorite_repo.FavoriteRepo;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;

import java.util.List;

public class FavoritePresenter {

    private FavoriteRepo favoriteRepo ;

    public FavoritePresenter(FavoriteRepo favoriteRepo) {
        this.favoriteRepo = favoriteRepo;
    }

    LiveData<List<FavoriteEntity>> getFavoriteMeals(){
        return favoriteRepo.getFavoriteMeals() ;
    }

    public void deleteMealFromFavorite(int id, String url, String name) {
        favoriteRepo.deleteMealFromFavorite(id , url , name);
    }
}
