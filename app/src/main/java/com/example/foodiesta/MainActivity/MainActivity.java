package com.example.foodiesta.MainActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodiesta.R;
import com.example.foodiesta.Utilities.ConnectionState;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth ;
    private LottieAnimationView lottieAnimationView ;
    private FragmentContainerView fragmentContainerView ;
    private boolean connectionFlag = true  ;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        firebaseAuth = FirebaseAuth.getInstance() ;
        lottieAnimationView = findViewById(R.id.main_no_connection_animation) ;
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

         ConnectionState.observeInternetConnectivity(getApplicationContext()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        isConnected -> {
                            if(!isConnected){
                                fragmentContainerView.setVisibility(View.GONE);
                                lottieAnimationView.setVisibility(View.VISIBLE);
                                lottieAnimationView.playAnimation();
                                bottomNavigationView.setBackgroundColor(getColor(R.color.gray));
                                connectionFlag = false ;
                                setStatusBarColor(R.color.gray);
                                Log.i("connection", "no connection");
                            }else{
                                fragmentContainerView.setVisibility(View.VISIBLE);
                                lottieAnimationView.setVisibility(View.GONE);
                                lottieAnimationView.cancelAnimation();
                                bottomNavigationView.setBackgroundColor(getColor(R.color.purple));
                                connectionFlag = true ;
                                setStatusBarColor(R.color.purple);
                                Log.i("connection", " connection");
                            }
                        }
                );

    }

    @Override
    protected void onStart() {
        super.onStart();
       NavController navController =  Navigation.findNavController(this ,R.id.fragmentContainerView) ;
        NavigationUI.setupWithNavController(bottomNavigationView ,navController );
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (!connectionFlag && firebaseAuth.getCurrentUser() == null) {
                   return false ;
                } else if (!connectionFlag) {
                    if(item.getItemId() == R.id.homeFragment || item.getItemId() == R.id.searchFragment || item.getItemId() == R.id.profileFragment){
                        fragmentContainerView.setVisibility(View.GONE);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        lottieAnimationView.playAnimation();
                        return false ;
                    }else {
                        fragmentContainerView.setVisibility(View.VISIBLE);
                        lottieAnimationView.setVisibility(View.GONE);
                        lottieAnimationView.cancelAnimation();
                        return NavigationUI.onNavDestinationSelected(item, navController);
                    }
                }
                else {
                    if (item.getItemId() == R.id.favoriteFragment2 || item.getItemId() == R.id.calenderFragment2 || item.getItemId() == R.id.profileFragment) {
                        if (firebaseAuth.getCurrentUser() == null) {
                            showDialog(navController);
                            return false;
                        } else {
                            return NavigationUI.onNavDestinationSelected(item, navController);
                        }

                    } else {
                        return NavigationUI.onNavDestinationSelected(item, navController);
                    }
                }
            }
        });
    }

    public void showBottomNav(boolean show) {
        bottomNavigationView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    void showDialog(NavController navController){
        new MaterialAlertDialogBuilder(this)
                .setTitle("You need To login ")
                .setMessage("Sorry you need to login First to continue")
                .setPositiveButton("Login", (dialog, which) -> {
                    navController.navigate(R.id.loginFragment);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .show();
    }
    void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(color));
        }
    }
}



