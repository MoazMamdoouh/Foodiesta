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
import com.example.foodiesta.Data.Remore_data.RegistrationRemoteFireBase;
import com.example.foodiesta.Data.Repository.Login.LoginRepo;
import com.example.foodiesta.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginFragment extends Fragment {

    private TextInputEditText emailEditText , passwordEditText;
    private Button loginBtn , registrationBtn ;
    private TextView forgetPasswordText ;
    private String chefEmail , password ;
    private FirebaseAuth firebaseAuth ;
    private FirebaseUser firebaseUser;
    private LottieAnimationView lottieAnimationView ;
    private ForgetPasswordBottomSheet forgetPasswordBottomSheet ;
    private LoginPresenter loginPresenter ;
    private View viewAtt ;

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
        viewAtt = view ;
        initUI(view);
        initPresenter();
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
    private void initPresenter(){
        RegistrationRemoteFireBase registrationRemoteFireBase = new RegistrationRemoteFireBase();
        LoginRepo loginRepo = new LoginRepo(registrationRemoteFireBase);
        loginPresenter  = new LoginPresenter(loginRepo , this );
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

          loginPresenter.requestLogin(chefEmail , password);

      });

    }

    private void checkForAccountValidation(View view) {
        if(firebaseUser != null) {
            firebaseUser.reload().addOnSuccessListener(r -> {
                if (firebaseUser.isEmailVerified()) {
                    hideLottieLoadingAnimation();
                    Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment);
                } else {
                    hideLottieLoadingAnimation();
                    emailEditText.setError("please verify Your Account First");
                }
            });
        }
    }

    private void clearFeilds(){
        emailEditText.setText("");
        passwordEditText.setText("");
    }

    public void LoginSuccess() {
        hideLottieLoadingAnimation();
        Navigation.findNavController(viewAtt).navigate(R.id.action_loginFragment_to_homeFragment);

    }

    public void loginFailed(String failed) {
        clearFeilds();
        hideLottieLoadingAnimation();
        Toast.makeText(getContext(), failed, Toast.LENGTH_SHORT).show();

    }
}