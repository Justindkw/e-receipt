package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

public class addReceipt extends AppCompatActivity {

    EditText amount;
    EditText company;
    Bitmap receiptPhoto;
    Button submitButton;

    static final int REQUEST_IMAGE_CAPTURE = 2;
    Button captureButton;
    ImageView imageDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reciept);

        captureButton = (Button) findViewById(R.id.captureButton);
        imageDisplay = (ImageView) findViewById(R.id.imageCapture);

        captureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                activeTakePhoto();
            }
        });
    }

    //PHOTO STUFF BEINGS HERE

    //asks for photo permission
    private void activeTakePhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
        } else {
            takePicture();
        }

    }
    //asks for photo permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 110) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }}
    //goes to camera activity
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //PHOTO STUFF ENDS HERE

    //returns the resulted receipt back to receiptFolder
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            receiptPhoto = (Bitmap)data.getExtras().get("data");
            imageDisplay.setImageBitmap(receiptPhoto);
        }



        submitButton = (Button) findViewById(R.id.doneButton);
        //need change. MAKE ID CHANGE



        final Intent intent = new Intent();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Receipt newReceipt = new Receipt(receiptPhoto,Integer.valueOf(amount.getText().toString()), company.getText().toString());
               // intent.putExtra("newReceipt",newReceipt);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
