package com.example.rainbow;

import android.content.Intent;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Start_Page extends AppCompatActivity {

    Button getStartedButton;
    ImageView logoImage, middleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_start_page);


        getStartedButton = findViewById(R.id.getStartedButton);
        logoImage = findViewById(R.id.logoImage);
        middleImage = findViewById(R.id.middleImage);



        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Get Started clicked");

                try {
                    Intent intent = new Intent(Start_Page.this, NavigationBar.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("MainActivity", "Error launching Home activity", e);
                    Toast.makeText(Start_Page.this, "Crash: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762322/al_fatah_dtphq1.png")
                .into(logoImage);
        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762227/mid_img_hwoifu.png")
                .into(middleImage);
    }
}
