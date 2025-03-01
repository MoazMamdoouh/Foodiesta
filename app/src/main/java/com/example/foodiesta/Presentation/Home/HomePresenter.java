package com.example.foodiesta.Presentation.Home;

import com.example.foodiesta.Data.Repository.Home_repo.HomeRepository;
import com.example.foodiesta.Utilities.FoodObjectResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter  {

    private HomeRepository repository ;
    private HomeFragment homeFragment ;

    public HomePresenter(HomeRepository repository , HomeFragment homeFragment) {
        this.repository = repository;
        this.homeFragment = homeFragment ;
    }

    public void getAllRemoteMeals(){

      Single<FoodObjectResponse> observable =  repository.getRandomMeals() ;
      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new SingleObserver<FoodObjectResponse>() {
                  @Override
                  public void onSubscribe(@NonNull Disposable d) {

                  }

                  @Override
                  public void onSuccess(@NonNull FoodObjectResponse foodObjectResponse) {
                      homeFragment.showRandomMeals(foodObjectResponse);
                  }

                  @Override
                  public void onError(@NonNull Throwable e) {
                      homeFragment.showError(e.getMessage());
                  }
              }) ;

    }
    public void getRandomMeal(){
      Single<FoodObjectResponse> observable =   repository.getRandomDailyMeal() ;
      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new SingleObserver<FoodObjectResponse>() {
                  @Override
                  public void onSubscribe(@NonNull Disposable d) {

                  }

                  @Override
                  public void onSuccess(@NonNull FoodObjectResponse foodObjectResponse) {
                      homeFragment.showRandomDailyMeal(foodObjectResponse);
                  }

                  @Override
                  public void onError(@NonNull Throwable e) {
                    homeFragment.showError(e.getMessage());
                  }
              }) ;
    }
}
