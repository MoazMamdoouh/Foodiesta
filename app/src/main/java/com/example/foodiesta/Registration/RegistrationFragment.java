package com.example.foodiesta.Registration;


import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;
import com.example.foodiesta.MainActivity.MainActivity;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.CustomDialog;
import com.example.foodiesta.Utilities.LoadingDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.IOException;

public class RegistrationFragment extends Fragment {

    private TextInputEditText chefNameEditText ,chefEmailEditText ,chefPasswordEditText ,confirmPasswordEditText;
    private Button loginBtn , registration ;
    private AppCompatButton guestBtn ;
    private RegistrationPresenter registrationPresenter ;
    private String chefName ,chefEmail , chefPassword , confirmPassword ;
    private View viewAtt ;
    private FirebaseUser firebaseUser ;
    private FirebaseAuth firebaseAuth ;
    private CustomDialog customDialog ;
    private LoadingDialog loadingDialog ;

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
        guestBtnClicked();
        ((MainActivity) requireActivity()).showBottomNav(false);

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
            Log.i("TAG", "onStart: " + firebaseUser.isEmailVerified());
        checkForVerification(viewAtt);
    }

    private void loginBtnClicked(View view) {
        loginBtn.setOnClickListener(clicked ->{
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
        });
    }

    private void initPresenter() {
        MealsRemoteFireBase mealsRemoteFireBase = new MealsRemoteFireBase();
        RegistrationRepo registrationRepo = new RegistrationRepo(mealsRemoteFireBase) ;
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
            }else {
                hideLoadingAnimation();
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

    private void checkForVerification(View view){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null) {
          currentUser.reload().addOnSuccessListener(r -> {
              if (currentUser.isEmailVerified()) {
                  customDialog.dismiss();
                  Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment);
              } else {
                  Log.i("TAG", "checkForVerification: not verified");
              }
          });
      }
    }

    void registrationSuccess(){
        clearTextFeilds();
        hideLoadingAnimation();
        customDialog.success("Registration Successfully" , "check your email to varify your account and SingIn");
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
        guestBtn = view.findViewById(R.id.registration_btn_guest);
        registration = view.findViewById(R.id.registration_btn_registration) ;
        customDialog = new CustomDialog(getContext()) ;
        loadingDialog = new LoadingDialog(getContext()) ;
    }

    private void showLoadingAnimation(){
       loadingDialog.showLoadingAnimation();
    }
    private void hideLoadingAnimation(){
        loadingDialog.hideDialog();
    }

    private void clearTextFeilds(){
        chefNameEditText.setText("");
        chefEmailEditText.setText("");
        chefPasswordEditText.setText("");
        confirmPasswordEditText.setText("");
    }

    private void guestBtnClicked(){
        guestBtn.setOnClickListener(
                clicked ->Navigation.findNavController(viewAtt).navigate(R.id.action_registrationFragment_to_homeFragment)
        );

    }



}