package com.example.foodiesta.Presentation.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodiesta.R;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {

    private TextInputEditText emailEditText , passwordEmailText ;
    private Button loginBtn , registrationBtn ;
    private TextView forgetPasswordText ;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view) {
        emailEditText = view.findViewById(R.id.login_ti_email) ;
        passwordEmailText = view.findViewById(R.id.login_ti_password);
        loginBtn = view.findViewById(R.id.login_btn_login) ;
        registrationBtn = view.findViewById(R.id.login_btn_registration);
        forgetPasswordText = view.findViewById(R.id.login_tv_forget_password) ;
    }
}