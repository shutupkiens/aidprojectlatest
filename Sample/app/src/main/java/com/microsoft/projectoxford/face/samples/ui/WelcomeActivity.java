package com.microsoft.projectoxford.face.samples.ui;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.projectoxford.face.samples.R;

public class WelcomeActivity extends AppCompatActivity {

    Button StartWelcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        StartWelcome = (Button)findViewById(R.id.startaid);
        StartWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), Homepage.class);


                startActivity(i);
            }
        });






    }
}