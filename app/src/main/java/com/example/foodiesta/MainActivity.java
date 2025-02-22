package com.example.foodiesta;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodiesta.Presentation.Home.HomeFragment;
import com.example.foodiesta.Presentation.Serach.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
}



