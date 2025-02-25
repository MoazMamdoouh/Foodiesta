package com.example.foodiesta.Utilities;


public interface OnFoodObjectResponse<T>{
    public void success(FoodObjectResponse foodObjectResponse);
    void successGetDailyRandomMeal(FoodObjectResponse foodObjectResponse) ;
    public void failed(String msg) ;
}
