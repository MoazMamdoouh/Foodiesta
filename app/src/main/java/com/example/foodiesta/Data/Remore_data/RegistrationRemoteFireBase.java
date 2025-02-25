package com.example.foodiesta.Data.Remore_data;

import android.widget.Toast;

import com.example.foodiesta.Model.Registration.ChefRegistrationPojo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationRemoteFireBase {

    private DatabaseReference databaseReference ;

    private String checkString = "" ;


    public RegistrationRemoteFireBase(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public String requestToRegisterChef(String chefName, String chefEmail, String chefPassword, String confirmPassword) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Chef");
        databaseReference.child(chefName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                   checkString = "exist" ;
                } else {
                    ChefRegistrationPojo chefPojo = new ChefRegistrationPojo(chefName, chefEmail, chefPassword, confirmPassword);
                    databaseReference.child(chefName).setValue(chefPojo).addOnCompleteListener(insert -> {
                        if (insert.isSuccessful()) {
                           checkString = "success";
                        } else {
                            checkString = "failed";
                        }
                    });
                }
            }
        });
        return checkString ;
    }
}
