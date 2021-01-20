package com.example.android.depressiontest;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimateActivity extends AppCompatActivity {
    AnimationDrawable avoAnimation;
    AnimationDrawable leafAnimation;
    AnimationDrawable berryAnimation;
    AnimationDrawable tomatoAnimation;
    AnimationDrawable onionAnimation;
    AnimationDrawable mushroomAnimation;
    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        //Avocado
        ImageView imageView1 = (ImageView) findViewById(R.id.image);
        imageView1.setBackgroundResource(R.drawable.animation_avo);
        avoAnimation = (AnimationDrawable) imageView1.getBackground();

        //Leafy Greens
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        imageView2.setBackgroundResource(R.drawable.animation_leaf);
        leafAnimation = (AnimationDrawable) imageView2.getBackground();

        //Banana
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        imageView3.setBackgroundResource(R.drawable.animation_berry);
        berryAnimation = (AnimationDrawable) imageView3.getBackground();

        //Tomato
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        imageView4.setBackgroundResource(R.drawable.animation_tomato);
        tomatoAnimation = (AnimationDrawable) imageView4.getBackground();

        //Onion
        ImageView imageView5 = (ImageView) findViewById(R.id.image5);
        imageView5.setBackgroundResource(R.drawable.animation_onion);
        onionAnimation = (AnimationDrawable) imageView5.getBackground();

        //Mushroom
        ImageView imageView6 = (ImageView) findViewById(R.id.image6);
        imageView6.setBackgroundResource(R.drawable.animation_mushroom);
        mushroomAnimation = (AnimationDrawable) imageView6.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        avoAnimation.start();
        leafAnimation.start();
        berryAnimation.start();
        tomatoAnimation.start();
        onionAnimation.start();
        mushroomAnimation.start();

    }
}
