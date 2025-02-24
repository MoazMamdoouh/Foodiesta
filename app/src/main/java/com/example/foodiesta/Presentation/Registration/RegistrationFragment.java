package com.example.foodiesta.Presentation.Registration;

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
import com.google.android.material.textfield.TextInputEditText;


public class RegistrationFragment extends Fragment {

    private TextInputEditText chefNameEditText ,chefEmailEditText ,chefPasswordEditText ,confirmPasswordEditText;
    private Button loginBtn , registration ;

    public RegistrationFragment() {
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
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        checkForEmptyEditText();
        registrationBtnClicked(view);
    }

    private void registrationBtnClicked(View view) {
        registration.setOnClickListener(click ->
                        Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment)
                );
    }

    private void checkForEmptyEditText() {
        if(chefNameEditText.getText() == null || chefNameEditText.getText().toString().trim().isEmpty()){
            registration.setClickable(false);
        }
        if(chefEmailEditText.getText() == null || chefEmailEditText.getText().toString().trim().isEmpty()){
            registration.setClickable(false);
        }
        if(chefPasswordEditText.getText() == null || chefPasswordEditText.getText().toString().trim().isEmpty()){
            registration.setClickable(false);
        }
        if(confirmPasswordEditText.getText() == null || confirmPasswordEditText.getText().toString().trim().isEmpty()){
            registration.setClickable(false);
        }
    }

    private void initUI(View view) {
        chefNameEditText = view.findViewById(R.id.registration_ti_chif_name);
        chefEmailEditText = view.findViewById(R.id.registration_ti_email);
        chefPasswordEditText = view.findViewById(R.id.registration_ti_password);
        confirmPasswordEditText = view.findViewById(R.id.registration_ti_confirm_password);
        loginBtn = view.findViewById(R.id.registration_btn_login) ;
        registration = view.findViewById(R.id.registration_btn_registration) ;
    }
}