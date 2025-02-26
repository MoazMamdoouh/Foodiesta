package com.example.foodiesta.Presentation.Splash;

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


public class SplashFragment extends Fragment {

    private LottieAnimationView lottieAnimationView;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.playAnimation();

                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_welcomeFragment);
            }
        } , 5000) ;
    }


}