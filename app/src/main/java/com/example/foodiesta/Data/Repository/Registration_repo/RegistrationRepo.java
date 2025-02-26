package com.example.foodiesta.Data.Repository.Registration_repo;

import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;
import com.example.foodiesta.Presentation.Registration.OnRegistrationResponse;

public class RegistrationRepo {
    private RegistrationRemoteFireBase registrationRemoteFireBase ;

    public RegistrationRepo(RegistrationRemoteFireBase registrationRemoteFireBase) {
        this.registrationRemoteFireBase = registrationRemoteFireBase;
    }

    public void   requestToRegisterChef(String chefName, String chefEmail
            , String chefPassword, OnRegistrationResponse onRegistrationResponse) {
          registrationRemoteFireBase.requestToRegisterChef(chefName,chefEmail,chefPassword
                , onRegistrationResponse);
    }
}
