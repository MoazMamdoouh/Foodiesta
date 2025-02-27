package com.example.foodiesta.Presentation.Login;

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
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginFragment extends Fragment {

    private TextInputEditText emailEditText , passwordEditText;
    private Button loginBtn , registrationBtn ;
    private TextView forgetPasswordText ;
    private String chefEmail , password ;
    private FirebaseAuth firebaseAuth ;
    private FirebaseUser firebaseUser;
    private LottieAnimationView lottieAnimationView ;
    private ForgetPasswordBottomSheet forgetPasswordBottomSheet ;

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
        loginBtnClicked(view);
        registrationBtnClicked(view);
        forgetPasswordClicked();
    }

    private void initUI(View view) {
        emailEditText = view.findViewById(R.id.login_ti_email) ;
        passwordEditText = view.findViewById(R.id.login_ti_password);
        loginBtn = view.findViewById(R.id.login_btn_login) ;
        registrationBtn = view.findViewById(R.id.login_btn_registration);
        forgetPasswordText = view.findViewById(R.id.login_tv_forget_password) ;
        lottieAnimationView = view.findViewById(R.id.login_lottie_file_loading);
        lottieAnimationView.setVisibility(View.GONE);
        forgetPasswordBottomSheet = new ForgetPasswordBottomSheet();
        //fire base init
        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseUser = firebaseAuth.getCurrentUser() ;
    }

    private void registrationBtnClicked(View view) {
        registrationBtn.setOnClickListener(clicked ->{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
        });
    }

    private void showLottieLoadingAnimation(){
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
    }
    private void  hideLottieLoadingAnimation(){
        lottieAnimationView.cancelAnimation();
        lottieAnimationView.setVisibility(View.GONE);
    }

    private void  forgetPasswordClicked(){
        forgetPasswordText.setOnClickListener(clicked -> {
            forgetPasswordBottomSheet.show(getActivity().getSupportFragmentManager(), "BottomSheetTag");
        });
    }

    private void loginBtnClicked(View view) {
      loginBtn.setOnClickListener(clicked ->{
          showLottieLoadingAnimation();
          chefEmail = emailEditText.getText().toString();
          password = passwordEditText.getText().toString();
          if (TextUtils.isEmpty(chefEmail)) {
              emailEditText.setError("chef Email is Required");
              return;
          }
          if (TextUtils.isEmpty(password)) {
              passwordEditText.setError("chef Password is Required");
              return;
          }
          if(password.length() < 6){
              passwordEditText.setError("password must have 6 or more characters");
              return;
          }


              firebaseAuth.signInWithEmailAndPassword(chefEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          if(checkForAccountValidation()) {
                              hideLottieLoadingAnimation();
                              Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                          }else {
                              hideLottieLoadingAnimation();
                              emailEditText.setError("please verify Your Account First");
                          }
                      } else {
                          hideLottieLoadingAnimation();
                          String errorMessage = task.getException().getMessage();
                          if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                              Toast.makeText(getContext(), "Invalid credentials. Please check your email and password.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                              Toast.makeText(getContext(), "No account found with this email. Please sign up.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                              Toast.makeText(getContext(), "This email is already in use by another account.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                              Toast.makeText(getContext(), "Weak password. Password should be at least 6 characters.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthEmailException) {
                              Toast.makeText(getContext(), "Invalid email format. Please enter a valid email address.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthActionCodeException) {
                              Toast.makeText(getContext(), "Invalid or expired action code. Try again.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthRecentLoginRequiredException) {
                              Toast.makeText(getContext(), "Recent login required. Please log in again.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                              Toast.makeText(getContext(), "User account has been disabled or deleted.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthMultiFactorException) {
                              Toast.makeText(getContext(), "Multi-factor authentication required. Check your email for verification.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthWebException) {
                              Toast.makeText(getContext(), "Error with web authentication. Try again later.", Toast.LENGTH_LONG).show();
                          } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                              Toast.makeText(getContext(), "Password is too weak. Try using a stronger password.", Toast.LENGTH_LONG).show();
                          } else {
                              Toast.makeText(getContext(), "Authentication failed: " + errorMessage, Toast.LENGTH_LONG).show();
                          }

                      }
                  }
              });
      });

    }

    private boolean checkForAccountValidation() {
       return  firebaseUser.isEmailVerified() ;
    }

}