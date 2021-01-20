package com.microsoft.projectoxford.face.samples.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.microsoft.projectoxford.face.samples.R;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Nullable;

//import static com.microsoft.projectoxford.face.samples.ui.ResultFaceActivity.activity_history_list;

public class UserActivity extends AppCompatActivity {

    public final static String TAG = "Jinx";
    private static final int GALLERY_INTENT_CODE = 1023;
    private static final String Date = "date";
    private static final String Result = "result";

    private static final String NewDate = "newdate";
    private static final String NewResult = "newresult";


    TextView fullName, email, phone, verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    //Button resendCode;
    Button changeProfileImage;
    //FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;
    //String History;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);


        phone = findViewById(R.id.textViewUserPhone);
        fullName = findViewById(R.id.textViewUserName);
        email = findViewById(R.id.textViewUserEmail);
        //resetPassLocal = findViewById(R.id.resetPasswordLocal);

        profileImage = findViewById(R.id.ProfilePicCircularImageView);
        changeProfileImage = findViewById(R.id.changeProfile);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);

            }
        });

        //StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        //profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        //@Override
        //public void onSuccess(Uri uri) {
        //Picasso.get().load(uri).into(profileImage);
        //}
        //});

        //resendCode = findViewById(R.id.resendCode);
        //verifyMsg = findViewById(R.id.verifyMsg);


        userId = fAuth.getCurrentUser().getUid();
        //user = fAuth.getCurrentUser();

        //if(!user.isEmailVerified()){
        //verifyMsg.setVisibility(View.VISIBLE);
        //resendCode.setVisibility(View.VISIBLE);

        //resendCode.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(final View v) {

        //user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
        //@Override
        //public void onSuccess(Void aVoid) {
        //Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
        //}
        //}).addOnFailureListener(new OnFailureListener() {
        //@Override
        //public void onFailure(@NonNull Exception e) {
        //Log.d("tag", "onFailure: Email not sent " + e.getMessage());
        //}
        //});
        //}
        // });
        //}


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    phone.setText(documentSnapshot.getString("phone"));
                    fullName.setText(documentSnapshot.getString("username"));
                    email.setText(documentSnapshot.getString("email"));

                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        changeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);


                //i.putExtra("fullName",fullName.getText().toString());
                //i.putExtra("email",email.getText().toString());
                //i.putExtra("phone",phone.getText().toString());
                //startActivity(i);
//

            }
        });

        LoadHistory();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageURI = data.getData();
                //profileImage.setImageURI(imageURI);

                uploadImageToFirebase(imageURI);
            }
        }

        //public void logout(View view) {
        //FirebaseAuth.getInstance().signOut();//logout
        //startActivity(new Intent(getApplicationContext(),Login.class));
        //finish();
        //}

    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(UserActivity.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //@Override
    //public void onStart() {
    //super.onStart();
    //activity_history_list.add(UserActivity.this.getClass().getSimpleName());
    //}
    //documentReference.get()
    //.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
    //@Override
    //public void onSuccess(DocumentSnapshot documentSnapshot) {
    //if (documentSnapshot.exists()) {
    //String FullName = documentSnapshot.getString(Name);

    public void LoadHistory() {
        //FirebaseSt database = FirebaseDatabase.getInstance();
        Button ClearBT=(Button)findViewById(R.id.ClearHistoryButton);
        TextView txDate = new TextView(UserActivity.this);
        TextView txDate2 = new TextView(UserActivity.this);
        TextView txResults = new TextView(UserActivity.this);
        TextView txResults2 = new TextView(UserActivity.this);
        ImageView imgLine = new ImageView(UserActivity.this);
        ImageView imgLine2 = new ImageView(UserActivity.this);

        DocumentReference db = fStore.collection("users").document(userId).collection("Result").document("History");

        final LinearLayout LLHistroyView = (LinearLayout) findViewById(R.id.LLHistoryView);

        db.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {

                    //txDate.setText(documentSnapshot.getString("date"));
                    //txResults.setText(documentSnapshot.getString("result"));

                    Log.d(TAG, "Total Value is: ");

                    LLHistroyView.removeAllViewsInLayout();

                    String Datedb = documentSnapshot.getString(Date);
                    String Scoredb = documentSnapshot.getString(Result);

                    String NewDatedb = documentSnapshot.getString(NewDate);
                    String NewScoredb = documentSnapshot.getString(NewResult);



                    final LinearLayout LL1 = new LinearLayout(UserActivity.this);
                    LL1.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    LL1.setOrientation(LinearLayout.VERTICAL);
                    LL1.setPadding(10, 10, 10, 10);

                    LLHistroyView.addView(LL1);



                    //Date
                    txDate.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    txDate.setTypeface(null, Typeface.BOLD);
                    // txDate.setTextSize(18);
                    txDate.setText(Datedb);  // Name
                    txDate.setVisibility(View.VISIBLE);
                    //txUserName.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    LL1.addView(txDate);

                    //Results
                    txResults.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //txResults.setTextSize(10);
                    txResults.setText("Note: " + Scoredb);
                    LL1.addView(txResults);



                    Drawable DOCDrawableLine = getResources().getDrawable(R.color.BlackColor);
                    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    imgLine.setLayoutParams(layoutParams1);
                    imgLine.setImageDrawable(DOCDrawableLine);
                    LL1.addView(imgLine);

                    //Date2
                    txDate2.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    txDate2.setTypeface(null, Typeface.BOLD);
                    // txDate.setTextSize(18);
                    txDate2.setText(NewDatedb);  // Name
                    if (NewDatedb == null) {
                        txDate2.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        txDate.setVisibility(View.VISIBLE);
                    }
                    //txUserName.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    LL1.addView(txDate2);

                    //Results2
                    txResults2.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //txResults.setTextSize(10);
                    txResults2.setText("Note: " + NewScoredb);
                    if (NewScoredb == null) {
                        txResults2.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        txResults2.setVisibility(View.VISIBLE);
                    }
                    LL1.addView(txResults2);


                    Drawable DOCDrawableLine2 = getResources().getDrawable(R.color.BlackColor);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    imgLine2.setLayoutParams(layoutParams2);
                    imgLine2.setImageDrawable(DOCDrawableLine2);

                    if (NewDatedb == null && NewScoredb == null) {
                        imgLine2.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        imgLine2.setVisibility(View.VISIBLE);
                    }

                    LL1.addView(imgLine2);




                    ClearBT.setVisibility(View.VISIBLE);

                } else {

                    ClearBT.setVisibility(View.GONE);
                    txDate.setVisibility(View.GONE);
                    txDate2.setVisibility(View.GONE);
                    txResults.setVisibility(View.GONE);
                    txResults2.setVisibility(View.GONE);
                    imgLine.setVisibility(View.GONE);
                    imgLine2.setVisibility(View.GONE);



                    Log.d("tag", "onEvent: Document do not exists");

                }


            }
            //documentReference.keepSynced(true);
        });


    }

    public void DeleteHistory(View v){
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        DocumentReference documentReference = fStore.collection("users").document(userId).collection("Result").document("History");
        documentReference.delete();

    }


}