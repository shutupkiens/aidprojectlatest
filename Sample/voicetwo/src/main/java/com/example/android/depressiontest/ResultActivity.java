package com.example.android.depressiontest;

import android.content.ComponentName;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

//import junit.framework.Test;

public class ResultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "TAG";
    TextView TestResult;
    TextView Disclaimer;
    Button viewRec;
    int nilaiInt;
    //ImageView ScoreRange;
    private static final String Name = "FullName";
    String VoiceEmo;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String DocumentName;
    String depression;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();



        TestResult = (TextView) findViewById(R.id.TestResult);
        //Disclaimer = (TextView) findViewById(R.id.Disclaimer);
        //ScoreRange = (ImageView) findViewById(R.id.ScoreRange);

        //MainMenu = findViewById(R.id.ButtonMenu);

        //getName = getIntent().getStringExtra("nameofuser");
        nilaiInt = getIntent().getIntExtra("nilai", 0);
        VoiceEmo = getIntent().getStringExtra("voiceemotion");

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String FullName = documentSnapshot.getString(Name);

        //Neutrality
        if (nilaiInt <= 4 && "neutrality".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 9 && "neutrality".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 14 && "neutrality".equals(VoiceEmo)) {
            depression = "Mild Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Mild Depression"+
                    "\n\n\n This indicates that you may have mild depression. Please contact recommended hotline numbers if your symptoms worsen and affects your daily activities."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
        } else if (nilaiInt <= 22 && "neutrality".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt > 22 && "neutrality".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        }


        //Happiness
        if (nilaiInt <= 4 && "happiness".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 9 && "happiness".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 14 && "happiness".equals(VoiceEmo)) {
            depression = "Mild Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Mild Depression"+
                    "\n\n\n This indicates that you may have mild depression. Please contact recommended hotline numbers if your symptoms worsen and affects your daily activities."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
        } else if (nilaiInt <= 22 && "happiness".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt > 22 && "happiness".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        }


        //Sadness
        if (nilaiInt <= 4 && "sadness".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 9 && "sadness".equals(VoiceEmo)) {
            depression = "Mild Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Mild Depression"+
                    "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
        } else if (nilaiInt <= 14 && "sadness".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt <= 22 && "sadness".equals(VoiceEmo)) {
            depression = "Moderate to Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate to Severe Depression"+
                    "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt > 22 && "sadness".equals(VoiceEmo)) {
            depression = "Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Severe Depression"+
                    "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
        }

        //Anger
        if (nilaiInt <= 4 && "anger".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 9 && "anger".equals(VoiceEmo)) {
            depression = "Mild Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Mild Depression"+
                    "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
        } else if (nilaiInt <= 14 && "anger".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt <= 22 && "anger".equals(VoiceEmo)) {
            depression = "Moderate to Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate to Severe Depression"+
                    "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt > 22 && "anger".equals(VoiceEmo)) {
            depression = "Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Severe Depression"+
                    "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
        }

        //Fear
        if (nilaiInt <= 4 && "fear".equals(VoiceEmo)) {
            depression = "No Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: No Depression"+
                    "\n\n\n This indicates that you have no depression."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
        } else if (nilaiInt <= 9 && "fear".equals(VoiceEmo)) {
            depression = "Mild Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Mild Depression"+
                    "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
        } else if (nilaiInt <= 14 && "fear".equals(VoiceEmo)) {
            depression = "Moderate Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate Depression"+
                    "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt <= 22 && "fear".equals(VoiceEmo)) {
            depression = "Moderate to Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Moderate to Severe Depression"+
                    "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
        } else if (nilaiInt > 22 && "fear".equals(VoiceEmo)) {
            depression = "Severe Depression";
            TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Voice Emotion: " + VoiceEmo + "\n AID Questionnaire Score: " + nilaiInt +
                    "\n Total Result: Severe Depression"+
                    "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                    "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
        }

                        } else {
                            Toast.makeText( ResultActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            //Log.d("tag", "onEvent: Document do not exists");
                        }


                        documentReference.get().addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText( ResultActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                Log.d("tag", e.toString());

                            }
                        });

                    }
                });

        //Disclaimer.setText("\nPHQ-9, Patient Health Questionnaire 9:-" + "\nDeveloped by Drs Robert L Spitzer, Janet B.W. Williams, Kurt Kroenke and colleages, with an educational grant from Pfizer Inc."
        // + "\nNo permission required to reproduce, translate, display or contribute.");

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        int hour = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        int date = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int AMPMint =c.get(Calendar.AM_PM);
        String AMPM="AM";
        if(AMPMint==1){
            AMPM="PM";

        }

        if(hour==0){
            hour=12;
        }



        if(min<=9){
            String o="0"+String.valueOf(min);
            min=Integer.valueOf(o);
        }



        final String Hour = String.valueOf(hour);
        final String Min = String.valueOf(min);
        final String Date = String.valueOf(date);
        final String Month = String.valueOf(month + 1);
        final String Year = String.valueOf(year);

        final String finalAMPM = AMPM;


        viewRec = (Button) findViewById(R.id.recList);
        viewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = fAuth.getCurrentUser().getUid();
                //ScoreData = "Score - " + calndr.getTime();
                DocumentName = Date + "/" + Month + "/" + Year + ", " + Hour + ":" + Min+" "+ finalAMPM;

                {
                    DocumentReference documentReference = fStore.collection("users").document(userID).collection("Result").document("ScoreDataVoice");
                    Map<String, Object> user = new HashMap<>();
                    //user.put("date-" + DocumentName2, DocumentName);
                    user.put("result-" + DocumentName, depression);
                    //user.put("Gender",genderM);
                    //user.put("Gender",genderF);

                    //documentReference.child("date").setValue(Date + "/" + Month + "/" + Year + ", " + Hour + ":" + Min+" "+ finalAMPM);
                    //documentReference.child("result").setValue(depression);

                    documentReference.set(user, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: Data has been saved " + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });
                }

                DocumentReference documentReference2 = fStore.collection("users").document(userID).collection("Result").document("History");
                Map<String,Object> user2 = new HashMap<>();
                user2.put("newdate" ,DocumentName);
                user2.put("newresult" ,depression);
                //user.put("Gender",genderM);
                //user.put("Gender",genderF);

                //documentReference.child("date").setValue(Date + "/" + Month + "/" + Year + ", " + Hour + ":" + Min+" "+ finalAMPM);
                //documentReference.child("result").setValue(depression);

                documentReference2.set(user2,SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Data has been saved "+ userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });



                Intent r = new Intent(getApplicationContext(), TreatmentActivity.class);
                startActivity(r);
            }
        });
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