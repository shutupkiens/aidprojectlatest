package com.example.android.depressiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;


public class ExercisePageActivity extends AppCompatActivity {
    Button BtnInfo;
    Button BtnMild;
    Button BtnSevere;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercisepage);

        BtnInfo = findViewById(R.id.btnExeInfo);
        BtnMild = findViewById(R.id.BtnMild);
        BtnSevere = findViewById(R.id.btnSevere);

        BtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ExerciseInfo.class);
                startActivity(i);
            }
        });

        BtnMild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(), MildVidActivity.class);
                startActivity(b);
            }
        });


        BtnSevere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w = new Intent(getApplicationContext(), SevereVidActivity.class);
                startActivity(w);
            }
        });
    }
}