package com.made.fajaregafirmansyah.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.made.fajaregafirmansyah.R;
import com.made.fajaregafirmansyah.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);

        //load gambar splashscreen
        Glide.with(this).load(R.drawable.bg_splash).into(binding.imgSplash);

        //di set waktu lama di halaman splashscren
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //perpindahan dari splashscreen ke halaman film
                startActivity(new Intent(getApplicationContext(), FilmActivity.class));
                finish();
            }
        }, 2000L); //2000 L = 2 detik
    }
}
