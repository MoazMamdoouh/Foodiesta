package com.example.foodiesta.Search;

import com.example.foodiesta.Search.Category.CategoryObjectResponse;

public interface SearchShowResponse {
    void onShowResponseOfRandomListByFilter(CategoryObjectResponse randomMealsResponse) ;

    void failureResponse(String msg) ;
}
