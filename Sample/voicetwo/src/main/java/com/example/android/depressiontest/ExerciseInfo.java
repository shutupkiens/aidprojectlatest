package com.example.android.depressiontest;

import android.os.Bundle;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseInfo extends AppCompatActivity {


    AnimationDrawable runAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseinfo);

        //Run
        ImageView imageView6 = (ImageView) findViewById(R.id.imagerun);
        imageView6.setBackgroundResource(R.drawable.animation_run);
        runAnimation = (AnimationDrawable) imageView6.getBackground();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        runAnimation.start();


    }
}
