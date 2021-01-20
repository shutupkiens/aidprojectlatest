package com.microsoft.projectoxford.face.samples.ui;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class StartActivityFace extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText UserName;
    EditText Age;
    RadioButton Male;
    RadioButton Female;
    //RadioButton Age1;
    //RadioButton Age2;
    //RadioButton Age3;
    //RadioButton Age4;
    //RadioButton Age5;
    //RadioButton Age6;
    Button SubmitButton;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.android.depressiontest.R.layout.activity_start);

        UserName = (EditText) findViewById(com.example.android.depressiontest.R.id.Username);
        Male = findViewById(com.example.android.depressiontest.R.id.male);
        Female = findViewById(com.example.android.depressiontest.R.id.female);
        Age = findViewById(com.example.android.depressiontest.R.id.Age);
        SubmitButton = (Button)findViewById(com.example.android.depressiontest.R.id.btnSubmit);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String FullName = UserName.getText().toString();
                 String age = Age.getText().toString();
                 String genderM    = Male.getText().toString();
                 String genderF    = Female.getText().toString();

                if (UserName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please insert your name",
                            Toast.LENGTH_SHORT).show();
                }
                else if (Age.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please insert your age",
                            Toast.LENGTH_SHORT).show();
                }
                else {


                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("FullName",FullName);
                    user.put("Age",age);
                    user.put("Gender",genderM);
                    user.put("Gender",genderF);

                    documentReference.set(user,  SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
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

                    Intent i=new Intent(getApplicationContext(), WelcomeActivity.class);
                    i.putExtra("nameofuser", UserName.getText().toString());
                    startActivity(i);
                }


            }
        });
    }
}