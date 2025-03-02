package com.example.foodiesta.Data.Remore_data;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodiesta.Calender.Calender.CalenderEntity;
import com.example.foodiesta.Favorite.Favorite.FavoriteEntity;
import com.example.foodiesta.Login.OnLoginResponse;
import com.example.foodiesta.Profile.OnFireStoreResponse;
import com.example.foodiesta.Registration.OnRegistrationResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthWebException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsRemoteFireBase {
    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firestore ;
    private FirebaseUser firebaseUser ;
    private String userId;
    public MealsRemoteFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public void requestToRegisterChef(String chefName, String chefEmail
            , String chefPassword , OnRegistrationResponse onRegistrationResponse) {



        firebaseAuth.createUserWithEmailAndPassword(chefEmail , chefPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
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

    public void requestToLogin(String email , String password , OnLoginResponse onLoginResponse){

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    onLoginResponse.onLoginSuccess("Success");
                } else {
                    String errorMessage = task.getException().getMessage();
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                       onLoginResponse.onLoginFailed("Invalid credentials. Please check your email and password.");
                    } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        onLoginResponse.onLoginFailed("No account found with this email. Please sign up.");
                    } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        onLoginResponse.onLoginFailed("This email is already in use by another account.");
                    } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                        onLoginResponse.onLoginFailed("Weak password. Password should be at least 6 characters.");
                    } else if (task.getException() instanceof FirebaseAuthEmailException) {
                        onLoginResponse.onLoginFailed( "Invalid email format. Please enter a valid email address.");
                    } else if (task.getException() instanceof FirebaseAuthActionCodeException) {
                        onLoginResponse.onLoginFailed("Invalid or expired action code. Try again.");
                    } else if (task.getException() instanceof FirebaseAuthRecentLoginRequiredException) {
                        onLoginResponse.onLoginFailed("Recent login required. Please log in again.");
                    } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        onLoginResponse.onLoginFailed("User account has been disabled or deleted.");
                    } else if (task.getException() instanceof FirebaseAuthMultiFactorException) {
                        onLoginResponse.onLoginFailed("Multi-factor authentication required. Check your email for verification.");
                    } else if (task.getException() instanceof FirebaseAuthWebException) {
                        onLoginResponse.onLoginFailed("Error with web authentication. Try again later.");
                    } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                        onLoginResponse.onLoginFailed("Password is too weak. Try using a stronger password.");
                    } else {
                        onLoginResponse.onLoginFailed("Authentication failed: " + errorMessage);
                    }

                }
            }
        });
    }

    public void insertFavoriteMealsToServer(String userId, FirebaseFirestore firebaseFirestore
            , List<FavoriteEntity> response , OnFireStoreResponse onFireStoreResponse) {

        for (FavoriteEntity meal : response) {
            firebaseFirestore.collection("users_backUp")
                    .document(userId)
                    .collection("favorite_meals")
                    .document(String.valueOf(meal.getMealId()))
                    .set(meal)
                    .addOnSuccessListener(aVoid ->  onFireStoreResponse.successInsertionToServer("favorite"))
                    .addOnFailureListener(e -> Log.e("FirestoreError", "Backup failed", e));
        }
    }

    public void downloadFavoriteMeals(String userId , FirebaseFirestore firebaseFirestore ,OnFireStoreResponse onFireStoreResponse ) {
        firebaseFirestore.collection("users_backUp")
                .document(userId)
                .collection("favorite_meals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FavoriteEntity> favoriteEntities = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                FavoriteEntity meal = document.toObject(FavoriteEntity.class);
                                favoriteEntities.add(meal);
                            }
                            onFireStoreResponse.successDownload(favoriteEntities , "favorite");
                        } else {
                            Log.e("FirestoreError", "Error getting meals", task.getException());
                        }
                    }
                });
    }

    public void insertCalenderMealsToServer(List<CalenderEntity> calenderEntities ,FirebaseFirestore firebaseFirestore
     , String userID , OnFireStoreResponse onFireStoreResponse) {
        for (CalenderEntity calender : calenderEntities) {
            firebaseFirestore.collection("users_backUp")
                    .document(userID)
                    .collection("calender_meals")
                    .document(String.valueOf(calender.getMealId()))
                    .set(calender)
                    .addOnSuccessListener(aVoid ->  onFireStoreResponse.successInsertionToServer("calender"))
                    .addOnFailureListener(e -> Log.e("FirestoreError", "Backup failed", e));
        }
    }

    public void downLoadAllCalenderMeals(String userId, FirebaseFirestore firebaseFirestore, OnFireStoreResponse onFireStoreResponse) {
        firebaseFirestore.collection("users_backUp")
                .document(userId)
                .collection("calender_meals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FavoriteEntity> favoriteEntities = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                FavoriteEntity meal = document.toObject(FavoriteEntity.class);
                                favoriteEntities.add(meal);
                            }
                            onFireStoreResponse.successDownload(favoriteEntities , "calender");
                        } else {
                            Log.e("FirestoreError", "Error getting meals", task.getException());
                        }
                    }
                });

    }
}
