package com.example.foodiesta.Data.Remore_data;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodiesta.Presentation.Registration.OnRegistrationResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationRemoteFireBase {
    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firestore ;
    private String userId;
    public RegistrationRemoteFireBase() {

    }

    public void requestToRegisterChef(String chefName, String chefEmail
            , String chefPassword , OnRegistrationResponse onRegistrationResponse) {

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        firebaseAuth.createUserWithEmailAndPassword(chefEmail , chefPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    firebaseUser.sendEmailVerification().addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            userId = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("chef_data").document(userId);
                            Map<String , Object> chefsMap = new HashMap<>() ;
                            chefsMap.put("chefName" , chefName) ;
                            chefsMap.put("chefEmail" , chefEmail) ;
                            chefsMap.put("chefPassword" , chefPassword) ;
                            documentReference.set(chefsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                  onRegistrationResponse.onSuccessRegistration("Success");
                                }
                            });
                        }
                    }).addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           onRegistrationResponse.onFailedRegistration(e.getMessage().toString());
                        }
                    });
                }else {
                    onRegistrationResponse.onFailedRegistration(task.getException().getMessage());
                }
            }
        });
    }
}
