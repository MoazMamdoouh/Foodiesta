package com.example.foodiesta.Data.Remore_data;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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

    public Single getMealDetails(int id ){
        return api.getMealDetails(id) ;
    }

    public Single getAllCategories(){
       return api.getAllCategoryNames();
    }


    public Single getListOfSpacificCategory(String categoryName) {
       return  api.getListOfIngredients(categoryName) ;
    }

    public Single getListOfAllIngredients(){
       return api.getAllIngredientsNames("list");

    }

    public Single getListOfSpacificIngredient(String ingredientName) {
       return api.getListOfIngredients(ingredientName) ;
    }

    public Single getListOfCountries() {
        return  api.getAllCountriesNames("list");
    }

    public Single getListOfSpacificCountry(String itemName) {
       return  api.getListOFSpacificCountry(itemName) ;
    }
}
