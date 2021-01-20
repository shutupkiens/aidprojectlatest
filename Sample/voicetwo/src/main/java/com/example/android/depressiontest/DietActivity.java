package com.example.android.depressiontest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class DietActivity extends AppCompatActivity {

    private Button btnDash;
    private Button btnMedi;
    private ImageView imageDash;
    private ImageView imageMedi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        //Buttons
        btnDash = findViewById(R.id.btnDash);
        btnMedi = findViewById(R.id.btnMedi);

        //Images
        imageDash = findViewById(R.id.imgDash);
        imageMedi = findViewById(R.id.imgMedi);

        btnDash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.android.depressiontest.DietActivity.this, ViewDash.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(com.example.android.depressiontest.DietActivity.this, imageDash, ViewCompat.getTransitionName(imageDash));
                startActivity(intent, options.toBundle());
            }
        });

        btnMedi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.android.depressiontest.DietActivity.this, ViewMed.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(com.example.android.depressiontest.DietActivity.this, imageMedi, ViewCompat.getTransitionName(imageMedi));
                startActivity(intent, options.toBundle());
            }
        });

    }
}
