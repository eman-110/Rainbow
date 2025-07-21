package com.example.rainbow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageButton middleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        middleImage = findViewById(R.id.logoButton);

        middleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Start_Page.class);
                startActivity(intent);
            }
        });
        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762322/al_fatah_dtphq1.png")
                .into(middleImage);
    }
}