package com.example.foodiesta.Presentation.Serach;

import android.util.Log;

import com.example.foodiesta.Data.Repository.Search_repo.SearchRepo;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;

public class SearchPresenter implements SearchGateWay {

    private SearchRepo searchRepo ;
    private SearchShowResponse searchShowResponse ;

    public SearchPresenter(SearchShowResponse searchShowResponse) {
        this.searchRepo = new SearchRepo(this) ;
        this.searchShowResponse = searchShowResponse ;
    }

    void getListOfIngredient(){
        searchRepo.getListOfIngredient();
    }

    @Override
    public void getListOfIngredients(RandomMealsResponse randomMealsResponse) {
        searchShowResponse.onShowResponse(randomMealsResponse);
    }
}
