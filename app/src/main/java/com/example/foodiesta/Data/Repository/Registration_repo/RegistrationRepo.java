package com.example.foodiesta.Data.Repository.Registration_repo;

import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;

public class RegistrationRepo {
    private RegistrationRemoteFireBase registrationRemoteFireBase ;

    public RegistrationRepo(RegistrationRemoteFireBase registrationRemoteFireBase) {
        this.registrationRemoteFireBase = registrationRemoteFireBase;
    }

    public String  requestToRegisterChef(String chefName, String chefEmail, String chefPassword, String confirmPassword) {
        return registrationRemoteFireBase.requestToRegisterChef(chefName,chefEmail,chefPassword,confirmPassword);
    }
}
