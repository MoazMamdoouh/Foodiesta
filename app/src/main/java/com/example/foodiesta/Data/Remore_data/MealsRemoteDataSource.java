package com.example.foodiesta.Data.Remore_data;

import com.example.foodiesta.Data.Repository.Search_repo.SearchRepoGateWay;
import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.Model.Home.List_meals.RandomMealsResponse;
import com.example.foodiesta.Model.Home.Random_meal.RandomDailyMealResponse;
import com.example.foodiesta.Utilities.OnDetailedResponse;
import com.example.foodiesta.Utilities.OnResponseSend;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {

    private final static String BASE_URL ="https://www.themealdb.com/api/json/v1/1/";
    public static MealsRemoteDataSource instance = null;
    Api api ;
    private MealsRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         api = retrofit.create(Api.class);
    }
    public static MealsRemoteDataSource  getInstance(){
        if(instance == null){
            instance = new MealsRemoteDataSource() ;
        }
        return instance ;
    }


    public void getRandomMealsResponse(OnResponseSend onResponseSend){

        Call<RandomMealsResponse> responseCall = api.getListOfMeals("");
        responseCall.enqueue(new Callback<RandomMealsResponse>() {
            @Override
            public void onResponse(Call<RandomMealsResponse> call, Response<RandomMealsResponse> response) {
                onResponseSend.success(response.body());
            }

            @Override
            public void onFailure(Call<RandomMealsResponse> call, Throwable t) {
                onResponseSend.failed("Error :" + t.getMessage());
            }
        });
    }

    public void getRandomDailyMeal(OnResponseSend onResponseSend) {
        Call<RandomDailyMealResponse> responseCall = api.getRandomDailyMeal() ;
        responseCall.enqueue(new Callback<RandomDailyMealResponse>() {
            @Override
            public void onResponse(Call<RandomDailyMealResponse> call, Response<RandomDailyMealResponse> response) {
                onResponseSend.dailyMealSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RandomDailyMealResponse> call, Throwable t) {
                onResponseSend.failed("feild in daily meal : " + t.getMessage());
            }
        });

    }

    public void getMealDetails(int id  , OnDetailedResponse onDetailedResponse){
        Call<DetailsResponse> responseCall = api.getMealDetails(id) ;
        responseCall.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                onDetailedResponse.Success(response.body());
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                onDetailedResponse.failed(t.getMessage());
            }
        });
    }

    public void getListOfIngredient(SearchRepoGateWay searchRepoGateWay){

        Call<RandomMealsResponse> responseCall = api.getListOfMeals("");
        responseCall.enqueue(new Callback<RandomMealsResponse>() {
            @Override
            public void onResponse(Call<RandomMealsResponse> call, Response<RandomMealsResponse> response) {
                searchRepoGateWay.getRandomListByFilter(response.body() , "ingredient");
            }

            @Override
            public void onFailure(Call<RandomMealsResponse> call, Throwable t) {
                searchRepoGateWay.getErrorMsg("Error :" + t.getMessage());
            }
        });
    }

    public void getListOfCountries(SearchRepoGateWay searchRepoGateWay){

        Call<RandomMealsResponse> responseCall = api.getListOfCountries("Canadian");
        responseCall.enqueue(new Callback<RandomMealsResponse>() {
            @Override
            public void onResponse(Call<RandomMealsResponse> call, Response<RandomMealsResponse> response) {
                searchRepoGateWay.getRandomListByFilter(response.body() , "countries");
            }

            @Override
            public void onFailure(Call<RandomMealsResponse> call, Throwable t) {
            }
        });
    }

    public void getListOfCategories(SearchRepoGateWay searchRepoGateWay , String category){

        Call<RandomMealsResponse> responseCall = api.getListOfCategory(category);
        responseCall.enqueue(new Callback<RandomMealsResponse>() {
            @Override
            public void onResponse(Call<RandomMealsResponse> call, Response<RandomMealsResponse> response) {
                searchRepoGateWay.getRandomListByFilter(response.body() , "category");
            }

            @Override
            public void onFailure(Call<RandomMealsResponse> call, Throwable t) {
                searchRepoGateWay.getErrorMsg("Error :" + t.getMessage());
            }
        });
    }
}
