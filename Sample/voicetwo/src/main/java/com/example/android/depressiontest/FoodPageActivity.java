package com.example.android.depressiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;


public class FoodPageActivity extends AppCompatActivity {
    Button BtnInfo;
    Button BtnDiet;
    Button BtnMildFood;
    Button BtnSevFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodpage);

        BtnInfo = findViewById(R.id.btnFoodInfo);
        BtnDiet = findViewById(R.id.BtnDiet);
        BtnMildFood = findViewById(R.id.btnFood1);
        BtnSevFood = findViewById(R.id.btnFood2);

        BtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FoodInfo.class);
                startActivity(i);
            }
        });

        BtnDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(), DietActivity.class);
                startActivity(b);
            }
        });


        BtnMildFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w = new Intent(getApplicationContext(), Activity1.class);
                startActivity(w);
            }
        });

        BtnSevFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w = new Intent(getApplicationContext(), Activity11.class);
                startActivity(w);
            }
        });
    }


    }
