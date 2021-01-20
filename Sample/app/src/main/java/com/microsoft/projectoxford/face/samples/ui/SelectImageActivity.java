package com.microsoft.projectoxford.face.samples.ui;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v4.content.FileProvider;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.MediaStore.AUTHORITY;

import com.microsoft.projectoxford.face.samples.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


// The activity for the user to select a image and to detect faces in the image.
public class SelectImageActivity extends AppCompatActivity {
    // Flag to indicate the request of the next task to be performed
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_SELECT_IMAGE_IN_ALBUM = 1;

    public static final int CAMERA_PERMISSION_REQUEST_CODE = 4192;


    // The URI of photo taken with camera
    private Uri mUriPhotoTaken;

    // When the activity is created, set all the member variables to initial state.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

    }

    // Save the activity state when it's going to stop.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("ImageUri", mUriPhotoTaken);
    }

    // Recover the saved state when the activity is recreated.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUriPhotoTaken = savedInstanceState.getParcelable("ImageUri");
    }

    // Deal with the result of selection of the photos and faces.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
            case REQUEST_SELECT_IMAGE_IN_ALBUM:
                if (resultCode == RESULT_OK) {
                    Uri imageUri;
                    if (data == null || data.getData() == null) {
                        imageUri = mUriPhotoTaken;
                    } else {
                        imageUri = data.getData();
                    }
                    Intent intent = new Intent();
                    intent.setData(imageUri);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }


    // When the button of "Take a Photo with Camera" is pressed.
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void takePhoto(View v) {
        if(checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED && checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            invokeCamera();
        } else {
            // let's request permission
            String[] permissionRequest = {CAMERA, WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionRequest, CAMERA_PERMISSION_REQUEST_CODE);


            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //if(intent.resolveActivity(getPackageManager()) != null) {
            // Save the photo taken to a temporary file.
            //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            //try {
            //File file = File.createTempFile("IMG_", ".jpg", storageDir);
            // mUriPhotoTaken = Uri.fromFile(file);
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);
            //startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            //} catch (IOException e) {
            //setInfo(e.getMessage());
            // }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {

            // we have heard back from our request for camera and write external storage
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            } else {
                Toast.makeText(this, R.string.cannotopencamera, Toast.LENGTH_LONG).show();
            }
        }

    }

    private void invokeCamera () {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        mUriPhotoTaken = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues());

        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);

        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);

        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //get a file reference
        //mUriPhotoTaken = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", createImageFile());

        //tell the camera where to save the image
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);

        //tell the camera to request WRITE permission
        //intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //startActivityForResult(intent, REQUEST_TAKE_PHOTO);

    }

    private File createImageFile() {
        //the public picture directory
        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //timestamp makes unique name
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());

        //put together the directory and the timestamp to make a unique image location.
        File imageFile = new File(picturesDirectory, "picture" + timestamp + ".jpg");
        //imageFilePath = imageFile.getAbsolutePath();

        return imageFile;
    }

    // When the button of "Select a Photo in Album" is pressed.
    public void selectImageInAlbum(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
        }
    }

    // Set the information panel on screen.
    private void setInfo(String info) {
        TextView textView = (TextView) findViewById(R.id.info);
        textView.setText(info);
    }


}

