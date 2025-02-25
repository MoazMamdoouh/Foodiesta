package com.example.foodiesta.Presentation.Serach;

import com.example.foodiesta.Model.Search.Category.CategoryObjectResponse;

public interface SearchShowResponse {
    void onShowResponseOfRandomListByFilter(CategoryObjectResponse randomMealsResponse) ;

    void failureResponse(String msg) ;
}
