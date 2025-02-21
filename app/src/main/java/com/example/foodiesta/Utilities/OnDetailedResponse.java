package com.example.foodiesta.Utilities;

import com.example.foodiesta.Model.Details.DetailsResponse;

public interface OnDetailedResponse {
    public void Success(DetailsResponse detailsResponse);
    public void failed(String msg) ;
}
