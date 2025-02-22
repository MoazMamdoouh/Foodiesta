package com.example.foodiesta.Data.Repository.Deatails_repo;


import com.example.foodiesta.Data.Local_data.MealsLocalDataSource;
import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Model.Favorite.FavoriteEntity;
import com.example.foodiesta.Utilities.OnDetailedResponse;

public class DetailsRepo {
    private MealsRemoteDataSource mealsRemoteDataSource ;
    private MealsLocalDataSource mealsLocalDataSource ;

    public DetailsRepo(MealsRemoteDataSource mealsRemoteDataSource , MealsLocalDataSource mealsLocalDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource ;
    }

    public void getMealDetails(int id  , OnDetailedResponse onDetailedResponse){
        mealsRemoteDataSource.getMealDetails(id , onDetailedResponse);
    }
    public void insertMealToFavorite(int id , String mealUrl , String mealName ) {
        mealsLocalDataSource.insertFavoriteMeal(id , mealUrl , mealName);
    }

}
