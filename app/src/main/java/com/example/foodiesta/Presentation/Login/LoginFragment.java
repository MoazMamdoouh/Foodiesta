package com.example.foodiesta.Presentation.Login;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodiesta.Data.Remore_data.MealsRemoteFireBase;
import com.example.foodiesta.Data.Repository.Login.LoginRepo;
import com.example.foodiesta.MainActivity;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.LoadingDialog;
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
    private ForgetPasswordBottomSheet forgetPasswordBottomSheet ;
    private LoginPresenter loginPresenter ;
    private View viewAtt ;
    private LoadingDialog loadingDialog ;

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
        loadingDialog = new LoadingDialog(getContext()) ;
        initUI(view);
        initPresenter();
        loginBtnClicked(view);
        registrationBtnClicked(view);
        forgetPasswordClicked();

        ((MainActivity) requireActivity()).showBottomNav(false);
    }

    private void initUI(View view) {
        emailEditText = view.findViewById(R.id.login_ti_email) ;
        passwordEditText = view.findViewById(R.id.login_ti_password);
        loginBtn = view.findViewById(R.id.login_btn_login) ;
        registrationBtn = view.findViewById(R.id.login_btn_registration);
        forgetPasswordText = view.findViewById(R.id.login_tv_forget_password) ;
        forgetPasswordBottomSheet = new ForgetPasswordBottomSheet();
        //fire base init
        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseUser = firebaseAuth.getCurrentUser() ;
    }
    private void initPresenter(){
        MealsRemoteFireBase mealsRemoteFireBase = new MealsRemoteFireBase();
        LoginRepo loginRepo = new LoginRepo(mealsRemoteFireBase);
        loginPresenter  = new LoginPresenter(loginRepo , this );
    }
    private void registrationBtnClicked(View view) {
        registrationBtn.setOnClickListener(clicked ->{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
        });
    }
    private void  forgetPasswordClicked(){
        forgetPasswordText.setOnClickListener(clicked -> {
            forgetPasswordBottomSheet.show(getActivity().getSupportFragmentManager(), "BottomSheetTag");
        });
    }

    private void loginBtnClicked(View view) {
      loginBtn.setOnClickListener(clicked ->{
          loadingDialog.showLoadingAnimation();
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
                    Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_homeFragment);
                    Log.i("TAG", "checkForAccountValidation: true");
                } else {
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
      loadingDialog.hideDialog();
     // checkForAccountValidation(viewAtt);
        Navigation.findNavController(viewAtt).navigate(R.id.action_loginFragment_to_homeFragment);
    }

    public void loginFailed(String failed) {
        clearFeilds();

        Toast.makeText(getContext(), failed, Toast.LENGTH_SHORT).show();

    }
}