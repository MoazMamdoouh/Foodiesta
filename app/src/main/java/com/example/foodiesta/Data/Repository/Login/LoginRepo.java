package com.example.foodiesta.Data.Repository.Login;

import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;
import com.example.foodiesta.Presentation.Login.OnLoginResponse;

public class LoginRepo {

    private RegistrationRemoteFireBase registrationRemoteFireBase ;

    public LoginRepo(RegistrationRemoteFireBase registrationRemoteFireBase) {
        this.registrationRemoteFireBase = registrationRemoteFireBase;
    }

    public void requestToLogin(String email , String password , OnLoginResponse onLoginResponse){
        registrationRemoteFireBase.requestToLogin(email , password , onLoginResponse);
    }
}
