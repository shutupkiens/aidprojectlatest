package com.example.android.depressiontest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TreatmentActivity extends AppCompatActivity  {

    TextView Disclaimer;
    TextView WhatIsDepression;
    Button MainMenu;
    Button BtnExercise;
    Button BtnDiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        //Disclaimer = findViewById(R.id.infoDisclaimer);
        //WhatIsDepression = findViewById(R.id.DefineDepression);
        //MainMenu = findViewById(R.id.MainMenu);
        BtnExercise = findViewById(R.id.btnExercise);
        BtnDiet = findViewById(R.id.btnDiet);



        //MainMenu.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(i);
            //}
        //});

        BtnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ExercisePageActivity.class);
                startActivity(i);
            }
        });
        BtnDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FoodPageActivity.class);
                startActivity(i);
            }
        });


    }
    public void browser1(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nimh.nih.gov/health/publications/depression/index.shtml#pub6"));
        startActivity(browserIntent);
    }

    public void browser2(View view){
        Intent browser2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moodjuice.scot.nhs.uk/depression.asp"));
        startActivity(browser2Intent);
    }

}
