package com.example.foodiesta.Data.Remore_data;

import com.google.firebase.database.DatabaseReference;

public class RegistrationRemoteFireBase {

    private DatabaseReference databaseReference ;

    private String checkString = "" ;


    public RegistrationRemoteFireBase(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public String requestToRegisterChef(String chefName, String chefEmail, String chefPassword, String confirmPassword) {
      return null;
    }
}
