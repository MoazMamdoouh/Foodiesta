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

    void requestRandomListOfIngredient(){
        searchRepo.requestListOfIngredient();
    }

    void requestRandomListOfCountries(){
        searchRepo.requestListOfCountries() ;
    }
    void requestRandomListOfCategory(){searchRepo.requestRandomListOfCategory() ; }

    @Override
    public void getRandomListByFilterResponseSuccess(RandomMealsResponse randomMealsResponse , String query) {
        if(query.equals("ingredient")){
            Log.i("TAG", "presenter: ingredient ");
            searchShowResponse.onShowResponseOfRandomListByFilter(randomMealsResponse , query);
        }
        else if (query.equals("countries")) {
            Log.i("TAG", "presenter: countries");
            searchShowResponse.onShowResponseOfRandomListByFilter(randomMealsResponse , query);
        }
        else {
            Log.i("TAG", "presenter: category ");
            searchShowResponse.onShowResponseOfRandomListByFilter(randomMealsResponse , query);
        }    }

    @Override
    public void failureResponse(String msg) {

    }
}
