package com.example.foodiesta.Model.Search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchShowListResponse {
    @SerializedName("meals")
    private List<ListOfSearchShowListResponse> listOfSearchShowListResponses ;
    public List<ListOfSearchShowListResponse> getListOfSearchShowListResponses() {
        return listOfSearchShowListResponses;
    }

    public void setListOfSearchShowListResponses(List<ListOfSearchShowListResponse> listOfSearchShowListResponses) {
        this.listOfSearchShowListResponses = listOfSearchShowListResponses;
    }
}
