package com.project.seekxpot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeF homeF = new HomeF();
    PerfilF perfilF = new PerfilF();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeF).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeF).commit();
                    return true;
                } else if (item.getItemId() == R.id.perfil) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, perfilF).commit();
                    return true;
                }
                return false;
            }
        });

    }
}