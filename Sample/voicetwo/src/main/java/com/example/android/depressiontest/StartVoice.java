package com.example.android.depressiontest;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartVoice extends AppCompatActivity {
    Button StartQues;
    String Nama;
    //String VoiceEmo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startvoice);
        Nama = getIntent().getStringExtra("nameofuser");
        //VoiceEmo = getIntent().getStringExtra("voiceemotion");

        StartQues = (Button)findViewById(R.id.startques);
        StartQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), VoiceActivity.class);

                i.putExtra("nameofuser", Nama);
                //i.putExtra("voiceemotion", VoiceEmo);


                startActivity(i);
            }
        });






    }
}

