package com.example.android.depressiontest;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class ReccomendActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView Disclaimer;
    TextView WhatIsDepression;
    TextView HotlineNum;
    Button MainMenu;

    String userID;
    FirebaseAuth fAuth;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_navivoice);

        //Disclaimer = findViewById(R.id.infoDisclaimer);
        WhatIsDepression = findViewById(R.id.DefineDepression);
        HotlineNum = findViewById(R.id.contactlist);
        MainMenu = findViewById(R.id.MainMenu);

        fAuth = FirebaseAuth.getInstance();


        //Disclaimer.setText("Please note: This self-test is meant to give you insight in your mood state. "+
        //"This test is explicitly not suitable for diagnosis.This test cannot replace professional help. When in doubt, please contact your general practitioner." +
        //" No rights can be derived from the results of this test.");

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent r = new Intent(getApplicationContext(), TreatmentActivity.class);
                startActivity(r);

                //Intent i = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(i);
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

    public void browser3(View view){
        Intent browser3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aia.com.my/en/what-matters/seetheotherside/mental-health-helpline-resources.html"));
        startActivity(browser3Intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_MAIN);

        int itemId = item.getItemId();

        if (itemId == R.id.nav_account) {
            Log.i("MenuItem", "Account");
            intent.setComponent(new ComponentName("com.microsoft.projectoxford.face.samples", "com.microsoft.projectoxford.face.samples.ui.UserActivity"));
            startActivity(intent);

        } else if (itemId == R.id.nav_settings) {
            Log.i("MenuItem", "About");
            intent.setComponent(new ComponentName("com.microsoft.projectoxford.face.samples", "com.microsoft.projectoxford.face.samples.ui.AboutActivity"));
            startActivity(intent);

        } else if (itemId == R.id.nav_homepage) {
            Log.i("MenuItem", "Homepage");
            intent.setComponent(new ComponentName("com.microsoft.projectoxford.face.samples", "com.microsoft.projectoxford.face.samples.ui.Homepage"));
            startActivity(intent);

        } else if (itemId == R.id.nav_logout) {
            Log.i("MenuItem", "Logout");
            userID = fAuth.getCurrentUser().getUid();
            FirebaseAuth.getInstance().signOut();//logout
            intent.setComponent(new ComponentName("com.microsoft.projectoxford.face.samples", "com.microsoft.projectoxford.face.samples.ui.LoginActivity"));
            finish();
        } else {
            return false;
        }

        return false;
    }
}