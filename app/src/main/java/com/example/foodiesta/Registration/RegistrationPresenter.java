package com.example.foodiesta.Registration;

public class RegistrationPresenter implements OnRegistrationResponse {

    private RegistrationRepo registrationRepo ;
    private RegistrationFragment registrationFragment ;

    public RegistrationPresenter(RegistrationRepo registrationRepo , RegistrationFragment registrationFragment) {
        this.registrationRepo = registrationRepo;
        this.registrationFragment = registrationFragment ;
    }

    public void  requestToRegisterChef(String chefName, String chefEmail, String chefPassword) {
          registrationRepo.requestToRegisterChef(chefName,chefEmail,chefPassword,this);
    }

    @Override
    public void onSuccessRegistration(String success) {
        registrationFragment.registrationSuccess();
    }

    @Override
    public void onFailedRegistration(String error) {
        registrationFragment.registrationFailed(error);
    }
}
