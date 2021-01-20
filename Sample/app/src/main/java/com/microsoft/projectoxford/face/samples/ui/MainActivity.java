package com.microsoft.projectoxford.face.samples.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.depressiontest.VoiceActivity;
import com.microsoft.projectoxford.face.samples.R;

public class MainActivity extends AppCompatActivity {

    Button StartDetect;
    EditText UserName;
    String Nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nama = getIntent().getStringExtra("nameofuser");

        StartDetect = (Button) findViewById(R.id.btnDetect);
        StartDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(getApplicationContext(), DetectionActivity.class);
                d.putExtra("nameofuser", Nama);

                startActivity(d);
            }
        });

        if (getString(R.string.subscription_key).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        }
    }

    //public void detection(View view) {
    //Intent intent = new Intent(this, DetectionActivity.class);
    //startActivity(intent);
}

//public void detectionvoice(View view) {
//Intent intent = new Intent(this, com.example.android.depressiontest.MainActivity.class);
//startActivity(intent);
//}

//public void grouping(View view) {
//Intent intent = new Intent(this, GroupingActivity.class);
//startActivity(intent);
//}

//public void findSimilarFace(View view) {
//Intent intent = new Intent(this, FindSimilarFaceActivity.class);
//startActivity(intent);
//}

//public void identification(View view) {
//Intent intent = new Intent(this, IdentificationActivity.class);
//startActivity(intent);
//}


