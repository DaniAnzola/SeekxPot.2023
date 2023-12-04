package com.project.seekxpot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashArtActivity extends AppCompatActivity {

    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);

        SharedPreferences gp=getSharedPreferences("shared",MODE_PRIVATE);
        uid = gp.getString("user",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(uid==null){
                    Intent i = new Intent(SplashArtActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent j = new Intent(SplashArtActivity.this, HomeActivity.class);
                    startActivity(j);
                    finish();
                }

            }
        },1000);
    }
}