package com.example.foodiesta.Presentation.Registration;

import com.example.foodiesta.Data.Repository.Registration_repo.RegistrationRepo;

public class RegistrationPresenter {

    private RegistrationRepo registrationRepo ;

    public RegistrationPresenter(RegistrationRepo registrationRepo) {
        this.registrationRepo = registrationRepo;
    }

    public String  requestToRegisterChef(String chefName, String chefEmail, String chefPassword, String confirmPassword) {
        return registrationRepo.requestToRegisterChef(chefName,chefEmail,chefPassword,confirmPassword);
    }
}
