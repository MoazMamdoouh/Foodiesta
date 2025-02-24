package com.example.foodiesta.Presentation.Welcome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodiesta.R;


public class WelcomeFragment extends Fragment {

    private Button loginBtn ;
    private Button registrationBtn ;

    public WelcomeFragment() {
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
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        registrationBtnClicked(view);

    }

    private void registrationBtnClicked(View view) {
        registrationBtn.setOnClickListener(click ->
                        Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_registrationFragment)
                );
    }

    private void initUI(View view) {
        loginBtn = view.findViewById(R.id.welcome_btn_login) ;
        registrationBtn = view.findViewById(R.id.welcome_btn_registration) ;
    }
}