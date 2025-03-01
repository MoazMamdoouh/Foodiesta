package com.example.foodiesta.Data.Repository.Registration_repo;

import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;
import com.example.foodiesta.Presentation.Registration.OnRegistrationResponse;

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
