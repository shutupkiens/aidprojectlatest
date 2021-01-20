package com.microsoft.projectoxford.face.samples.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.projectoxford.face.samples.R;

public class StartQuestion extends AppCompatActivity {
    Button StartQues;

    String Nama;
    String EmotionType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startquestion);
        Nama = getIntent().getStringExtra("nameofuser");
        EmotionType = getIntent().getStringExtra("voiceemotion");

        StartQues = (Button)findViewById(R.id.startques);
        StartQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), QuestionFaceActivity.class);

                i.putExtra("nameofuser", Nama);
                i.putExtra("voiceemotion", EmotionType);


                startActivity(i);
            }
        });






    }
}
