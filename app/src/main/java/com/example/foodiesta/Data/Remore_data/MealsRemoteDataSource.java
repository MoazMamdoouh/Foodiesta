package com.example.foodiesta.Data.Remore_data;

import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.Model.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Model.Search.Ingredient.IngredientObjectResponse;
import com.example.foodiesta.Presentation.Serach.SearchGateWay;
import com.example.foodiesta.Utilities.OnDetailedResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
         api = retrofit.create(Api.class);
    }
    public static MealsRemoteDataSource  getInstance(){
        if(instance == null){
            instance = new MealsRemoteDataSource() ;
        }
        return instance ;
    }


    public Single getRandomMealsResponse(){
        return api.getListOfIngredients("");
    }

    public Single getRandomDailyMeal() {
        return api.getRandomDailyMeal() ;
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

    public void getAllCategories(SearchGateWay searchGateWay){
        Call<CategoryObjectResponse> responseCall = api.getListOfCategory("list") ;
        responseCall.enqueue(new Callback<CategoryObjectResponse>() {
            @Override
            public void onResponse(Call<CategoryObjectResponse> call, Response<CategoryObjectResponse> response) {
                searchGateWay.getListOfAllCategories(response.body() ,"category" );
            }

            @Override
            public void onFailure(Call<CategoryObjectResponse> call, Throwable t) {

            }
        });
    }


    public void getListOfSpacificCategory(SearchGateWay searchGateWay ,String categoryName) {
      /*  Call<FoodObjectResponse> responseCall = api.getListOfIngredients(categoryName) ;
        responseCall.enqueue(new Callback<FoodObjectResponse>() {
            @Override
            public void onResponse(Call<FoodObjectResponse> call, Response<FoodObjectResponse> response) {
                    searchGateWay.getListByFilter(response.body() , "category");
            }

            @Override
            public void onFailure(Call<FoodObjectResponse> call, Throwable t) {

            }
        });*/
    }

    public void getListOfAllIngredients(SearchGateWay searchGateWay){
        Call<IngredientObjectResponse> responseCall = api.getAllIngredientsNames("list");
        responseCall.enqueue(new Callback<IngredientObjectResponse>() {
            @Override
            public void onResponse(Call<IngredientObjectResponse> call, Response<IngredientObjectResponse> response) {
                searchGateWay.getListOfAllIngredients(response.body() );
            }

            @Override
            public void onFailure(Call<IngredientObjectResponse> call, Throwable t) {
                searchGateWay.failureResponse("Error :" + t.getMessage());
            }
        });
    }

    public void getListOfSpacificIngredient(SearchGateWay searchGateWay,String ingredientName) {
        /*Call<FoodObjectResponse> responseCall = api.getListOfIngredients(ingredientName) ;
        responseCall.enqueue(new Callback<FoodObjectResponse>() {
            @Override
            public void onResponse(Call<FoodObjectResponse> call, Response<FoodObjectResponse> response) {
                searchGateWay.getListByFilter(response.body() , "ingredient");
            }

            @Override
            public void onFailure(Call<FoodObjectResponse> call, Throwable t) {

            }
        });*/
    }

    public void getListOfCountries(SearchGateWay searchGateWay) {
        Call<CountryObjectResponse> countryObjectResponseCall = api.getAllCountriesNames("list");
        countryObjectResponseCall.enqueue(new Callback<CountryObjectResponse>() {
            @Override
            public void onResponse(Call<CountryObjectResponse> call, Response<CountryObjectResponse> response) {
                searchGateWay.getListOfAllCountries(response.body());
            }

            @Override
            public void onFailure(Call<CountryObjectResponse> call, Throwable t) {

            }
        });

    }

    public void getListOfSpacificCountry(SearchGateWay searchGateWay, String itemName) {
        Call<FoodObjectResponse> foodObjectResponseCall = api.getListOFSpacificCountry(itemName) ;
        foodObjectResponseCall.enqueue(new Callback<FoodObjectResponse>() {
            @Override
            public void onResponse(Call<FoodObjectResponse> call, Response<FoodObjectResponse> response) {
                    searchGateWay.getListByFilter(response.body() , "country");
            }

            @Override
            public void onFailure(Call<FoodObjectResponse> call, Throwable t) {

            }
        });
    }
}
