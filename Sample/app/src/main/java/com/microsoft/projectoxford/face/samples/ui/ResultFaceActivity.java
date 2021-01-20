package com.microsoft.projectoxford.face.samples.ui;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.android.depressiontest.TreatmentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.microsoft.projectoxford.face.samples.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Nullable;


public class ResultFaceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "TAG";
    TextView TestResult;
    TextView Disclaimer;
    Button viewRec;
    int nilaiInt;
    //ImageView ScoreRange;
    private static final String Name = "FullName";
    String getEmo;

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
        setContentView(R.layout.navi_result);

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


        //DocumentReference documentReference = fStore.collection("users").document(userID);
        //documentReference.get()
        //.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        //@Override
        //public void onSuccess(DocumentSnapshot documentSnapshot) {
        //if (documentSnapshot.exists()) {
        //String FullName = documentSnapshot.getString(Name);



        TestResult = (TextView) findViewById(com.example.android.depressiontest.R.id.TestResult);
        //Disclaimer = (TextView) findViewById(com.example.android.depressiontest.R.id.Disclaimer);
        //ScoreRange = (ImageView) findViewById(com.example.android.depressiontest.R.id.ScoreRange);

        //MainMenu = findViewById(R.id.ButtonMenu);

        //getName = getIntent().getStringExtra("nameofuser");
        nilaiInt = getIntent().getIntExtra("nilai", 0);
        getEmo = getIntent().getStringExtra("voiceemotion");

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String FullName = documentSnapshot.getString(Name);

                            //Neutral
                            if (nilaiInt <= 4 && "Neutral".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 9 && "Neutral".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 14 && "Neutral".equals(getEmo)) {
                                depression = "Mild Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Mild Depression"+
                                        "\n\n\n This indicates that you may have mild depression. Please contact recommended hotline numbers if your symptoms worsen and affects your daily activities."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
                            } else if (nilaiInt <= 22 && "Neutral".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt > 22 && "Neutral".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            }

                            //Happiness
                            if (nilaiInt <= 4 && "Happiness".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 9 && "Happiness".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 14 && "Happiness".equals(getEmo)) {
                                depression = "Mild Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Mild Depression"+
                                        "\n\n\n This indicates that you may have mild depression. Please contact recommended hotline numbers if your symptoms worsen and affects your daily activities."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
                            } else if (nilaiInt <= 22 && "Happiness".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt > 22 && "Happiness".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            }


                            //Sadness
                            if (nilaiInt <= 4 && "Sadness".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 9 && "Sadness".equals(getEmo)) {
                                depression = "Mild Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Mild Depression"+
                                        "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
                            } else if (nilaiInt <= 14 && "Sadness".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt <= 22 && "Sadness".equals(getEmo)) {
                                depression = "Moderate to Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate to Severe Depression"+
                                        "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt > 22 && "Sadness".equals(getEmo)) {
                                depression = "Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Severe Depression"+
                                        "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
                            }

                            //Anger
                            if (nilaiInt <= 4 && "Anger".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 9 && "Anger".equals(getEmo)) {
                                depression = "Mild Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Mild Depression"+
                                        "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
                            } else if (nilaiInt <= 14 && "Anger".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt <= 22 && "Anger".equals(getEmo)) {
                                depression = "Moderate to Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate to Severe Depression"+
                                        "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt > 22 && "Anger".equals(getEmo)) {
                                depression = "Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Severe Depression"+
                                        "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
                            }

                            //Fear
                            if (nilaiInt <= 4 && "Fear".equals(getEmo)) {
                                depression = "No Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n  Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: No Depression"+
                                        "\n\n\n This indicates that you have no depression."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest after one month in order to track improvements.");
                            } else if (nilaiInt <= 9 && "Fear".equals(getEmo)) {
                                depression = "Mild Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Mild Depression"+
                                        "\n\n\n This indicates that you may have mild depression. Please do recommended self-treatment activities and contact recommended hotline numbers if your symptoms worsen."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest twice a month in order to track improvements.");
                            } else if (nilaiInt <= 14 && "Fear".equals(getEmo)) {
                                depression = "Moderate Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate Depression"+
                                        "\n\n\n This indicates that you may have moderate depression. We gladly suggest you to get counselling and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt <= 22 && "Fear".equals(getEmo)) {
                                depression = "Moderate to Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Moderate to Severe Depression"+
                                        "\n\n\n This indicates that you may have moderate to severe depression. We gladly suggest you to seek active treatment with medication and/or therapy."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest weekly in order to track improvements.");
                            } else if (nilaiInt > 22 && "Fear".equals(getEmo)) {
                                depression = "Severe Depression";
                                TestResult.setText("\n Hi " + FullName + "\n\n\n\n\n Face Emotion: " + getEmo + "\n AID Questionnaire Score: " + nilaiInt +
                                        "\n Total Result: Severe Depression"+
                                        "\n\n\n This indicates that you may have severe depression. We gladly suggest you to refer yourself to mental health specialist."+
                                        "\n\n\n Please try our recommended treatment suggestions and we recommend you to retest daily in order to track improvements.");
                            }

                        } else {
                            Toast.makeText( ResultFaceActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            //Log.d("tag", "onEvent: Document do not exists");
                        }


                        documentReference.get().addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText( ResultFaceActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                Log.d("tag", e.toString());

                            }
                        });

                    }
                });

        //Disclaimer.setText("PHQ-9, Patient Health Questionnaire 9:-" + " Developed by Drs Robert L Spitzer, Janet B.W. Williams, Kurt Kroenke and colleages, with an educational grant from Pfizer Inc."
        //+ " No permission required to reproduce, translate, display or contribute.");

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

        String finalAMPM = AMPM;

        //Calendar calndr = Calendar.getInstance();
        //calndr.setTimeZone(TimeZone.getTimeZone("GMT+8"));



        viewRec = (Button) findViewById(com.example.android.depressiontest.R.id.recList);
        viewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = fAuth.getCurrentUser().getUid();
                //ScoreData = "Score - " + calndr.getTime();
                DocumentName = Date + "/" + Month + "/" + Year + ", " + Hour + ":" + Min+" "+ finalAMPM;
                //DocumentName2 = Date + "." + Month + "." + Year + ", " + Hour + ":" + Min+" "+ finalAMPM;
                //timestamp = FirebaseFirestore.Timestamp.fromDate(new Date(("December 25, 1993")));

                {
                    DocumentReference documentReference = fStore.collection("users").document(userID).collection("Result").document("ScoreDataFace");
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
                user2.put("date" ,DocumentName);
                user2.put("result" ,depression);
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
        Intent intent;

        switch (item.getItemId()) {
            case R.id.nav_account:
                Log.i("MenuItem", "Account");
                intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_settings:
                Log.i("MenuItem", "About");
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_homepage:
                Log.i("MenuItem", "Home");
                intent = new Intent(getApplicationContext(), Homepage.class);
                startActivity(intent);
                break;

            case R.id.nav_logout:
                Log.i("MenuItem", "Logout");
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;

            default:
                return false;
        }

        return false;
    }
}