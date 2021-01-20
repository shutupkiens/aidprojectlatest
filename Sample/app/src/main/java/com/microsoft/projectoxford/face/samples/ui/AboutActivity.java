package com.microsoft.projectoxford.face.samples.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.projectoxford.face.samples.R;

public class AboutActivity extends AppCompatActivity {



    TextView Disclaimer;
    TextView WhatIsDepression;
    TextView SelfGuide;
    //Button LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.microsoft.projectoxford.face.samples.R.layout.activity_about);

        Disclaimer = findViewById(R.id.InfoDisclaimer);
        WhatIsDepression = findViewById(R.id.definedepression);
        SelfGuide = findViewById(R.id.selfguide);
        //LogoutBtn = findViewById(R.id.btnlogout);


        //LogoutBtn.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(i);
            //}
        //});
    }

    public void browser1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nimh.nih.gov/health/publications/depression/index.shtml#pub6"));
        startActivity(browserIntent);
    }

    public void browser2(View view) {
        Intent browser2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moodjuice.scot.nhs.uk/depression.asp"));
        startActivity(browser2Intent);
    }

    public void fsktmweb (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fsktm.upm.edu.my/"));
        startActivity(browserIntent);
    }

    public void fsktmfb (View view) {
        Intent browser2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fcsitupm"));
        startActivity(browser2Intent);
    }

    public void fsktmig (View view) {
        Intent browser2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fsktm_upm/"));
        startActivity(browser2Intent);
    }
}