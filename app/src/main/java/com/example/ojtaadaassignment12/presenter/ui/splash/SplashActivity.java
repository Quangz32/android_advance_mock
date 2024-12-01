package com.example.ojtaadaassignment12.presenter.ui.splash;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.presenter.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splashImage = findViewById(R.id.splashImage);
        splashImage.setBackgroundResource(R.drawable.splash_animation);

        AnimationDrawable splashAnimation = (AnimationDrawable) splashImage.getBackground();
        splashAnimation.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1500); // Change duration as needed
    }
}