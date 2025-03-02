package com.example.foodiesta.Login;

import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;

public class LoginRepo {

    private MealsRemoteFireBase mealsRemoteFireBase;

    public LoginRepo(MealsRemoteFireBase mealsRemoteFireBase) {
        this.mealsRemoteFireBase = mealsRemoteFireBase;
    }

    public void requestToLogin(String email , String password , OnLoginResponse onLoginResponse){
        mealsRemoteFireBase.requestToLogin(email , password , onLoginResponse);
    }
}
