package com.example.android.depressiontest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.projects.alshell.vokaturi.Emotion;
import com.projects.alshell.vokaturi.EmotionProbabilities;
import com.projects.alshell.vokaturi.Vokaturi;
import com.projects.alshell.vokaturi.VokaturiException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.projects.alshell.vokaturi.Vokaturi.logD;

public class VoiceActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 5;

    private ImageView emojiEmotionImageView;
    private TextView EmotionTextView;
    private ProgressBar progressBarNeutrality;
    private ProgressBar progressBarHappiness;
    private ProgressBar progressBarSadness;
    private ProgressBar progressBarAnger;
    private ProgressBar progressBarFear;

    private TextView textViewNeutrality;
    private TextView textViewHappiness;
    private TextView textViewSadness;
    private TextView textViewAnger;
    private TextView textViewFear;

    private TextView actionStatus;

    private PlayPauseButton playPauseButton;

    private Vokaturi vokaturiApi;

    Button answerQuestion;
    String Nama;


    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String VoiceEmotion;
    String DocumentName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        Nama = getIntent().getStringExtra("nameofuser");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        try {
            logD("About to instantiate the library");
            vokaturiApi = Vokaturi.getInstance(VoiceActivity.this);
        } catch (VokaturiException e) {
            e.printStackTrace();
        }

        ActivityCompat.requestPermissions(VoiceActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);

        initializeViews();

        setListeners();
    }

    private void initializeViews() {
        emojiEmotionImageView = findViewById(R.id.emojiEmotionImageView);
        EmotionTextView = findViewById(R.id.EmotionTextView);
        progressBarNeutrality = findViewById(R.id.progressBarNeutrality);
        progressBarHappiness = findViewById(R.id.progressBarHappiness);
        progressBarSadness = findViewById(R.id.progressBarSadness);
        progressBarAnger = findViewById(R.id.progressBarAnger);
        progressBarFear = findViewById(R.id.progressBarFear);
        playPauseButton = findViewById(R.id.playPauseButton);

        textViewNeutrality = findViewById(R.id.textViewNeutrality);
        textViewHappiness = findViewById(R.id.textViewHappiness);
        textViewSadness = findViewById(R.id.textViewSadness);
        textViewAnger = findViewById(R.id.textViewAnger);
        textViewFear = findViewById(R.id.textViewFear);

        actionStatus = findViewById(R.id.actionStatus);
    }

    private void setListeners() {
        playPauseButton.setOnControlStatusChangeListener(new PlayPauseButton.OnControlStatusChangeListener() {
            @Override
            public void onStatusChange(View view, boolean state) {
                if (state) {
                    startListening();
                } else {
                    stopListening();
                }
            }

        });
    }

    @SuppressLint("SetTextI18n")
    private void startListening() {
        if (vokaturiApi != null) {
            try {
                setListeningUI();
                vokaturiApi.startListeningForSpeech();
            } catch (VokaturiException e) {
                setNotListeningUI();
                if (e.getErrorCode() == VokaturiException.VOKATURI_DENIED_PERMISSIONS) {
                    Toast.makeText(this, "Grant Microphone and Storage permissions in the app settings to proceed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "There was some problem to start listening audio", Toast.LENGTH_SHORT).show();
                }
            } catch (IllegalStateException e) {
                setNotListeningUI();
                e.printStackTrace();
            }
        }
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    private void setListeningUI() {
        actionStatus.setText("Press again to stop listening and analyze emotions");
        progressBarNeutrality.setIndeterminate(true);
        progressBarHappiness.setIndeterminate(true);
        progressBarSadness.setIndeterminate(true);
        progressBarAnger.setIndeterminate(true);
        progressBarFear.setIndeterminate(true);
        emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_default));
        EmotionTextView.setText(getResources().getString(R.id.EmotionTextView));
    }

    @SuppressLint("SetTextI18n")
    private void stopListening() {
        if (vokaturiApi != null) {
            setNotListeningUI();

            try {
                showMetrics(vokaturiApi.stopListeningAndAnalyze());
            } catch (VokaturiException e) {
                if (e.getErrorCode() == VokaturiException.VOKATURI_NOT_ENOUGH_SONORANCY) {
                    Toast.makeText(this, "Please speak a more clear and avoid noise around your environment", Toast.LENGTH_LONG).show();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void setNotListeningUI() {
        actionStatus.setText("Press the button and read the following sentence ");
        progressBarNeutrality.setIndeterminate(false);
        progressBarHappiness.setIndeterminate(false);
        progressBarSadness.setIndeterminate(false);
        progressBarAnger.setIndeterminate(false);
        progressBarFear.setIndeterminate(false);
    }


    @SuppressLint("SetTextI18n")
    private void showMetrics(EmotionProbabilities emotionProbabilities) {
        emotionProbabilities.scaledValues(5);

        logD("showMetrics for, " + emotionProbabilities.toString());
        textViewNeutrality.setText("Neutrality: " + emotionProbabilities.Neutrality);
        textViewHappiness.setText("Happiness: " + emotionProbabilities.Happiness);
        textViewSadness.setText("Sadness: " + emotionProbabilities.Sadness);
        textViewAnger.setText("Anger: " + emotionProbabilities.Anger);
        textViewFear.setText("Fear: " + emotionProbabilities.Fear);

        showEmojiBasedOnMetrics(emotionProbabilities);
        progressBarNeutrality.setProgress(normalizeForProgressBar(emotionProbabilities.Neutrality));
        progressBarHappiness.setProgress(normalizeForProgressBar(emotionProbabilities.Happiness));
        progressBarSadness.setProgress(normalizeForProgressBar(emotionProbabilities.Sadness));
        progressBarAnger.setProgress(normalizeForProgressBar(emotionProbabilities.Anger));
        progressBarFear.setProgress(normalizeForProgressBar(emotionProbabilities.Fear));

        showTextBasedOnMetrics(emotionProbabilities);
        progressBarNeutrality.setProgress(normalizeForProgressBar(emotionProbabilities.Neutrality));
        progressBarHappiness.setProgress(normalizeForProgressBar(emotionProbabilities.Happiness));
        progressBarSadness.setProgress(normalizeForProgressBar(emotionProbabilities.Sadness));
        progressBarAnger.setProgress(normalizeForProgressBar(emotionProbabilities.Anger));
        progressBarFear.setProgress(normalizeForProgressBar(emotionProbabilities.Fear));
    }

    private int normalizeForProgressBar(double val) {
        if (val < 1) {
            return (int) (val * 100);
        } else {
            return (int) (val * 10);
        }
    }

    private void showEmojiBasedOnMetrics(EmotionProbabilities emotionProbabilities) {
        Emotion capturedEmotion = Vokaturi.extractEmotion(emotionProbabilities);
        if (capturedEmotion == Emotion.Neutral) {
            emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_neutral));
        } else if (capturedEmotion == Emotion.Happy) {
            emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_happiness));
        } else if (capturedEmotion == Emotion.Sad) {
            emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_sadness));
        } else if (capturedEmotion == Emotion.Angry) {
            emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_anger));
        } else if (capturedEmotion == Emotion.Feared) {
            emojiEmotionImageView.setImageDrawable(getResources().getDrawable(R.drawable.emoji_fear));
        }
    }

    @SuppressLint("ResourceType")
    private void showTextBasedOnMetrics(EmotionProbabilities emotionProbabilities) {
        Emotion capturedEmotion = Vokaturi.extractEmotion(emotionProbabilities);
        if (capturedEmotion == Emotion.Neutral) {
            EmotionTextView.setText(getResources().getString(R.string.textNeutral));
        } else if (capturedEmotion == Emotion.Happy) {
            EmotionTextView.setText(getResources().getString(R.string.textHappy));
        } else if (capturedEmotion == Emotion.Sad) {
            EmotionTextView.setText(getResources().getString(R.string.textSad));
        } else if (capturedEmotion == Emotion.Angry) {
            EmotionTextView.setText(getResources().getString(R.string.textAnger));
        } else if (capturedEmotion == Emotion.Feared) {
            EmotionTextView.setText(getResources().getString(R.string.textFear));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Audio recording permissions denied.", Toast.LENGTH_SHORT).show();
            }
        }
        answerQuestion = (Button)findViewById(R.id.answerQuestion);
        answerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Emotion = EmotionTextView.getText().toString();

                userID = fAuth.getCurrentUser().getUid();
                VoiceEmotion = "VoiceEmotion";

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

                DocumentName2 = Date + "/" + Month + "/" + Year + ", " + Hour + ":" + Min+" "+ finalAMPM;
                Map<String,Object> user = new HashMap<>();
                user.put("VoiceEmotion-" + DocumentName2,Emotion);

                //user.put("timestamp", FieldValue.serverTimestamp());

                DocumentReference documentReference = fStore.collection("users").document(userID).collection("Result").document(VoiceEmotion);
                //documentReference.set(user);
                documentReference.set(user, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
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

                Intent i=new Intent(getApplicationContext(), StartQuestionVoice.class);
                i.putExtra("nameofuser", Nama);
                i.putExtra("voiceemotion", EmotionTextView.getText().toString());

                startActivity(i);
            }
        });
    }

}