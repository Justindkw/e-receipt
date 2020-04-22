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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class addReceipt extends AppCompatActivity {

    EditText amount;
    EditText company;
    Bitmap receiptPhoto;
    Button submitButton;
    Button captureButton;
    ImageView imageDisplay;

    String name;


    static final int REQUEST_IMAGE_CAPTURE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reciept);
        name = getIntent().getStringExtra("name");
        captureButton = (Button) findViewById(R.id.captureButton);
        imageDisplay = (ImageView) findViewById(R.id.imageCapture);
        submitButton = (Button) findViewById(R.id.doneBudgetButton);
        captureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                activeTakePhoto();
            }
        });
        final Intent intent = new Intent();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("addReceipt",name);
                Folder folder = GlobalFolderList.get(name);
                folder.addReceipt(new Receipt(receiptPhoto,10,"yes"));
                intent.putExtra("receiptPos",folder.size()-1);
                setResult(RESULT_OK,intent);
                finish();
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
    }
}
