package com.example.foodiesta.Welcome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodiesta.MainActivity.MainActivity;
import com.example.foodiesta.R;


public class WelcomeFragment extends Fragment {

    private AppCompatButton welcomeBtn ;
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
        ((MainActivity) requireActivity()).showBottomNav(false);
        initUI(view);
        welcomeBtnClicked(view);
    }

    private void welcomeBtnClicked(View view) {
        welcomeBtn.setOnClickListener(
                clicked -> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_registrationFragment)
        );
    }


    private void initUI(View view) {
        welcomeBtn = view.findViewById(R.id.welcome_btn) ;
    }

}