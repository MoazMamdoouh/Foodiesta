package com.example.foodiesta.Presentation.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;
import com.example.foodiesta.Data.Repository.Registration_repo.RegistrationRepo;
import com.example.foodiesta.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegistrationFragment extends Fragment {

    private TextInputEditText chefNameEditText ,chefEmailEditText ,chefPasswordEditText ,confirmPasswordEditText;
    private Button loginBtn , registration ;
    private RegistrationPresenter registrationPresenter ;
    private String chefName ,chefEmail , chefPassword , confirmPassword ;
    private View viewAtt ;
    private LottieAnimationView lottieAnimationView ;

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
        viewAtt = view ;
        initUI(view);
        initPresenter();
        registrationBtnClicked(view);
        loginBtnClicked(view);
    }

    private void loginBtnClicked(View view) {
        loginBtn.setOnClickListener(clicked ->{
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
        });
    }

    private void initPresenter() {
        RegistrationRemoteFireBase registrationRemoteFireBase = new RegistrationRemoteFireBase();
        RegistrationRepo registrationRepo = new RegistrationRepo(registrationRemoteFireBase) ;
        registrationPresenter = new RegistrationPresenter(registrationRepo , this );
    }

    private void registrationBtnClicked(View view) {
        registration.setOnClickListener(click -> {
            showLoadingAnimation();
            chefName = chefNameEditText.getText().toString();
            chefEmail = chefEmailEditText.getText().toString();
            chefPassword = chefPasswordEditText.getText().toString();
            confirmPassword = confirmPasswordEditText.getText().toString();
            if(checkForEmptyEditText()) {
                registrationPresenter.requestToRegisterChef(chefName, chefEmail, chefPassword);
            }
        });
    }

    private boolean checkForEmptyEditText() {

        if(TextUtils.isEmpty(chefName)){
            chefNameEditText.setError("chef name is Required");
            return false;
        }
        if(TextUtils.isEmpty(chefEmail)){
            chefEmailEditText.setError("chef Email is Required");
            return false;
        }
        if(TextUtils.isEmpty(chefPassword)) {
            chefPasswordEditText.setError("chef Password is Required");
            return false;
        }
        if(chefPassword.length() < 6){
            chefPasswordEditText.setError("password must have 6 or more characters");
            return false;
        }
        if(!chefPassword.equals(confirmPassword)){
            chefPasswordEditText.setError("password does not match ");
            confirmPasswordEditText.setError("password does not match ");
            return false;
        }
        else return true ;
    }

    void registrationSuccess(){
        hideLoadingAnimation();
        Navigation.findNavController(viewAtt).navigate(R.id.action_registrationFragment_to_homeFragment);
    }
    void registrationFailed(String msg){
        hideLoadingAnimation();
        Toast.makeText(getContext(), "Failed to Register" + msg, Toast.LENGTH_LONG).show();
    }

    private void initUI(View view) {
        chefNameEditText = view.findViewById(R.id.registration_ti_chif_name);
        chefEmailEditText = view.findViewById(R.id.registration_ti_email);
        chefPasswordEditText = view.findViewById(R.id.registration_ti_password);
        confirmPasswordEditText = view.findViewById(R.id.registration_ti_confirm_password);
        loginBtn = view.findViewById(R.id.registration_btn_login) ;
        registration = view.findViewById(R.id.registration_btn_registration) ;
        lottieAnimationView = view.findViewById(R.id.registration_lottie_file_loading) ;
        lottieAnimationView.setVisibility(View.GONE);
    }

    private void showLoadingAnimation(){
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
    }
    private void hideLoadingAnimation(){
        lottieAnimationView.setVisibility(View.GONE);
        lottieAnimationView.cancelAnimation();
    }
}