package com.example.foodiesta.Data.Remore_data;

import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.Model.Search.Country.CountryObjectResponse;
import com.example.foodiesta.Utilities.FoodObjectResponse;
import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;
import com.example.foodiesta.Model.Search.Ingredient.IngredientObjectResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("filter.php")
    Single<FoodObjectResponse> getListOfIngredients(@Query("i") String mealName) ;

    @GET("random.php")
    Single<FoodObjectResponse> getRandomDailyMeal();

    @GET("lookup.php")
    Call<DetailsResponse> getMealDetails(@Query("i") int id) ;


    @GET("categories.php")
    Call<CategoryObjectResponse> getListOfCategory(@Query("c") String categoryName) ;

    @GET("list.php")
    Call<IngredientObjectResponse> getAllIngredientsNames(@Query("i") String ingredientList) ;

    @GET("list.php")
    Call<CountryObjectResponse> getAllCountriesNames(@Query("a") String countryName) ;

    @GET("filter.php")
    Call<FoodObjectResponse> getListOFSpacificCountry(@Query("a") String country) ;






}
