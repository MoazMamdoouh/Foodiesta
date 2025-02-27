package com.example.foodiesta.Presentation.Splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.MainActivity;
import com.example.foodiesta.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment {

    private LottieAnimationView lottieAnimationView;
    private FirebaseAuth firebaseAuth ;
    private FirebaseUser firebaseUser ;
    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).showBottomNav(false);
        super.onViewCreated(view, savedInstanceState);
        lottieAnimationView = view.findViewById(R.id.splash_lottie_file) ;
        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseUser = firebaseAuth.getCurrentUser() ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.playAnimation();
                if (firebaseAuth.getCurrentUser() != null) {
                    if(firebaseUser.isEmailVerified()) {
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
                    }else {
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
                    }
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_welcomeFragment);
                }
            }
        } , 5000) ;
    }


}