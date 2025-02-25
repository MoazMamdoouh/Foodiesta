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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;
import com.example.foodiesta.Data.Repository.Registration_repo.RegistrationRepo;
import com.example.foodiesta.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationFragment extends Fragment {

    private TextInputEditText chefNameEditText ,chefEmailEditText ,chefPasswordEditText ,confirmPasswordEditText;
    private Button loginBtn , registration ;
    private RegistrationPresenter registrationPresenter ;
    private String chefName ,chefEmail , chefPassword , confirmPassword ;
    private DatabaseReference databaseReference ;
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

        initUI(view);
        initPresenter();
        //checkForEmptyEditText();
        registrationBtnClicked(view);

    }

    private void initPresenter() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Chef");
        RegistrationRemoteFireBase registrationRemoteFireBase = new RegistrationRemoteFireBase(databaseReference);
        RegistrationRepo registrationRepo = new RegistrationRepo(registrationRemoteFireBase) ;
        registrationPresenter = new RegistrationPresenter(registrationRepo);
    }

    private void registrationBtnClicked(View view) {
        registration.setOnClickListener(click -> {
                            lottieAnimationView.setVisibility(View.VISIBLE);
                            lottieAnimationView.playAnimation();
                            chefName = chefNameEditText.getText().toString();
                            chefEmail = chefEmailEditText.getText().toString();
                            chefPassword = chefPasswordEditText.getText().toString();
                            confirmPassword = confirmPasswordEditText.getText().toString();
                            if(checkForRegisterChef()){
                                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment);
                            }
                });
    }

    private boolean checkForRegisterChef() {
        boolean flag = false ;
        String chefStringFlag = registrationPresenter.requestToRegisterChef(chefName, chefEmail, chefPassword, confirmPassword);
        if(chefStringFlag.equals("exist")){
            lottieAnimationView.setVisibility(View.GONE);
            Toast.makeText(getContext(), "User Already Exist", Toast.LENGTH_SHORT).show();
            flag = false ;
        }
        if(chefStringFlag.equals("success")){
            lottieAnimationView.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Success to Register", Toast.LENGTH_SHORT).show();
            flag = true ;
        }
        return flag ;
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
        lottieAnimationView = view.findViewById(R.id.registration_lottie_file_loading) ;
        lottieAnimationView.setVisibility(View.GONE);
    }
}