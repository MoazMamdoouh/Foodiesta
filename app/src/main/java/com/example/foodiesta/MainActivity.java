package com.example.foodiesta;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodiesta.Presentation.Home.HomeFragment;
import com.example.foodiesta.Presentation.Serach.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    @Override
    protected void onStart() {
        super.onStart();
       NavController navController =  Navigation.findNavController(this ,R.id.fragmentContainerView) ;
        NavigationUI.setupWithNavController(bottomNavigationView ,navController );
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.favoriteFragment) {
                   // startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                } else {
                    return NavigationUI.onNavDestinationSelected(item, navController);
                }
            }
        });
    }

    public void showBottomNav(boolean show) {
        bottomNavigationView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}



