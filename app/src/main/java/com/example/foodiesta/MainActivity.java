package com.example.foodiesta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodiesta.Presentation.Profile.ProfileFragment;
import com.example.foodiesta.Utilities.ConnectionState;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth ;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        firebaseAuth = FirebaseAuth.getInstance() ;
        ConnectionState.observeInternetConnectivity(getApplicationContext()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                       isConnected -> {
                           if(!isConnected){
                               Toast.makeText(getApplication(),"no network" , Toast.LENGTH_LONG).show();
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
                if (item.getItemId() == R.id.favoriteFragment2 || item.getItemId() == R.id.calenderFragment2 || item.getItemId() == R.id.profileFragment ) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        showDialog(navController);
                        return false;
                    } else{
                        return NavigationUI.onNavDestinationSelected(item, navController);
                    }

                } else {
                    return NavigationUI.onNavDestinationSelected(item, navController);
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
}



