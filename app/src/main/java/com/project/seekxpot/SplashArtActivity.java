package com.project.seekxpot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashArtActivity extends AppCompatActivity {

    Animation animation1, animation2,animation3;

    ImageView txtView, combinedImageView,burbuja,burbuja1,burbuja2,burbuja3,burbuja4;

    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);

        burbuja = findViewById(R.id.burbuja);
        burbuja1 = findViewById(R.id.burbuja1);
        burbuja2 = findViewById(R.id.burbuja2);
        burbuja3 = findViewById(R.id.burbuja3);
        burbuja4 = findViewById(R.id.burbuja4);

        txtView = findViewById(R.id.fondo);

        combinedImageView = findViewById(R.id.cerveza);



        animation1 = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.buble_animacion);


        combinedImageView.setAnimation(animation1);
        txtView.startAnimation(animation2);


        burbuja.setAnimation(animation3);
        burbuja1.setAnimation(animation3);
        burbuja2.setAnimation(animation3);
        burbuja3.setAnimation(animation3);
        burbuja4.setAnimation(animation3);


        burbuja.startAnimation(animation3);
        burbuja1.startAnimation(animation3);
        burbuja2.startAnimation(animation3);
        burbuja3.startAnimation(animation3);
        burbuja4.startAnimation(animation3);





        SharedPreferences gp=getSharedPreferences("shared",MODE_PRIVATE);
        uid = gp.getString("user",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(uid==null){
                    combineImages();
                    Intent i = new Intent(SplashArtActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent j = new Intent(SplashArtActivity.this, HomeActivity.class);
                    startActivity(j);
                    finish();
                }

            }
        },10000);


    }

    private void combineImages() {
        // Aquí combina las imágenes imageView1 y imageView2 en una sola imagen
        // y establece esa imagen en combinedImageView
        // Puedes utilizar Bitmap y Canvas para combinar imágenes
        // ...

        // Muestra la imagen combinada
        combinedImageView.setVisibility(View.VISIBLE);
    }
}