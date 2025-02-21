package com.example.foodiesta.Presentation.Details;


import com.example.foodiesta.Data.Repository.Deatails_repo.DetailsRepo;
import com.example.foodiesta.Model.Details.DetailsResponse;
import com.example.foodiesta.Utilities.OnDetailedResponse;

public class DetailsPresenter implements OnDetailedResponse {

    private DetailsRepo detailsRepo ;
    private DetailsGateWay detailsGateWay ;

    public DetailsPresenter(DetailsRepo detailsRepo, DetailsGateWay detailsGateWay) {
        this.detailsRepo = detailsRepo;
        this.detailsGateWay = detailsGateWay;
    }

    void getMealDetails(int id) {
      detailsRepo.getMealDetails(id , this);
    }

    @Override
    public void Success(DetailsResponse detailsResponse) {
        detailsGateWay.ShowDetails(detailsResponse);
    }

    @Override
    public void failed(String msg) {

    }
}
