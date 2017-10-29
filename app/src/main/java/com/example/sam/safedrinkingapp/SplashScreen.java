package com.example.sam.safedrinkingapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(this)
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3500)
                .withBackgroundColor(Color.parseColor("#564421"))
                .withLogo(R.drawable.logo);

        View splashscreen = config.create();
        setContentView(splashscreen);
    }
}
