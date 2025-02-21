package com.example.foodiesta.Data.Repository.Deatails_repo;


import com.example.foodiesta.Data.Remore_data.MealsRemoteDataSource;
import com.example.foodiesta.Utilities.OnDetailedResponse;

public class DetailsRepo {
    private MealsRemoteDataSource mealsRemoteDataSource ;

    public DetailsRepo(MealsRemoteDataSource mealsRemoteDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
    }

    public void getMealDetails(int id  , OnDetailedResponse onDetailedResponse){
        mealsRemoteDataSource.getMealDetails(id , onDetailedResponse);
    }

}
