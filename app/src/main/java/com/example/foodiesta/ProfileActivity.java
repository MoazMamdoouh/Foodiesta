package com.example.foodiesta;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView  = findViewById(R.id.profile_bottom_nav) ;

    }

    @Override
    protected void onStart() {
        super.onStart();
        NavController navController =  Navigation.findNavController(this ,R.id.fragmentContainerView2) ;
        NavigationUI.setupWithNavController(bottomNavigationView ,navController );
    }
}