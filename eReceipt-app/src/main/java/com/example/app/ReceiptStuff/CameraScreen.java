package com.example.app.ReceiptStuff;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app.R;

public class CameraScreen extends AppCompatActivity {
//Justin's stuff
    //xml stuff
    Bitmap receiptPhoto;
    Button submitButton;
    Button captureButton;
    ImageView imageDisplay;
    //int to compare if it is our request
    static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //finds button and imageView from xml. get folderName from intent
        imageDisplay = findViewById(R.id.imageCapture);
        submitButton = findViewById(R.id.doneBudgetButton);
        captureButton = findViewById(R.id.captureButton);
        //sets capture button function
        captureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                activeTakePhoto();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //returns with photo
            setResult(RESULT_OK,new Intent().putExtra("photo",receiptPhoto));
            finish();
            }
        });
    }

    //PHOTO STUFF BEINGS HERE

    //asks for photo permission
    private void activeTakePhoto() {
        //checks if self permission is equal to camera activity permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            //if not take further action
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
        } else {
            //else take picture
            takePicture();
        }

    }
    //further action for permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 110) {
            //further action taken to take picture
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }}
    //goes to camera activity for photo
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    //PHOTO STUFF ENDS HERE

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        //returns the resulted receipt back to ReceiptFolder
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            receiptPhoto = (Bitmap)data.getExtras().get("data");
            imageDisplay.setImageBitmap(receiptPhoto);
        }
    }
}
