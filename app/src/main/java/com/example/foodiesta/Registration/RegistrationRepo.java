package com.example.foodiesta.Registration;

import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;

public class RegistrationRepo {
    private MealsRemoteFireBase mealsRemoteFireBase;

    public RegistrationRepo(MealsRemoteFireBase mealsRemoteFireBase) {
        this.mealsRemoteFireBase = mealsRemoteFireBase;
    }

    public void   requestToRegisterChef(String chefName, String chefEmail
            , String chefPassword, OnRegistrationResponse onRegistrationResponse) {
          mealsRemoteFireBase.requestToRegisterChef(chefName,chefEmail,chefPassword
                , onRegistrationResponse);
    }
}
