package com.example.foodiesta.Presentation.Login;

import com.example.foodiesta.Data.Repository.Login.LoginRepo;

public class LoginPresenter implements OnLoginResponse {

    private LoginRepo loginRepo;
    private LoginFragment loginFragment ;
    public LoginPresenter(LoginRepo loginRepo , LoginFragment loginFragment) {
        this.loginRepo = loginRepo;
        this.loginFragment = loginFragment ;
    }

    void requestLogin(String email , String password){
        loginRepo.requestToLogin(email , password , this);
    }

    @Override
    public void onLoginSuccess(String success) {
        loginFragment.LoginSuccess();
    }

    @Override
    public void onLoginFailed(String failed) {
        loginFragment.loginFailed(failed) ;
    }
}
