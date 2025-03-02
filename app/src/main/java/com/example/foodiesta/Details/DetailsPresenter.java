package com.example.foodiesta.Details;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter  {

    private DetailsRepo detailsRepo ;
    private DetailsFragment detailsFragment ;
    public DetailsPresenter(DetailsRepo detailsRepo, DetailsFragment detailsFragment) {
        this.detailsRepo = detailsRepo;
        this.detailsFragment = detailsFragment ;
    }

    void getMealDetails(int id) {
      Single<DetailsResponse> observable =  detailsRepo.getMealDetails(id);
      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new SingleObserver<DetailsResponse>() {
                  @Override
                  public void onSubscribe(@NonNull Disposable d) {

                  }

                  @Override
                  public void onSuccess(@NonNull DetailsResponse detailsResponse) {
                      detailsFragment.showDetails(detailsResponse);
                  }

                  @Override
                  public void onError(@NonNull Throwable e) {
                      detailsFragment.detailsError(e.getMessage());
                  }
              });
    }

    void insertMealToFavorite(int id , String url , String name ){
         detailsRepo.insertMealToFavorite(id , url , name );
    }
    void insertMealToCalender(int year , int month , int day , int id , String mealImage , String mealName){
        detailsRepo.insertMealToCalender(year , month , day , id , mealImage , mealName ) ;
    }
}
