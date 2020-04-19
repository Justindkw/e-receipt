package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FolderFile extends AppCompatActivity {

    private static  final String TAG = "folderFile";

    private Folder folder; //not Lucas' stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_file);
        Log.d(TAG, "onCreate: started.");
        folder = getIntent().getParcelableExtra("folder"); //not Lucas' stuff
    }


    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents"); //Log.d is just to check if the method is working

        if(getIntent().hasExtra("name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String folderName = getIntent().getStringExtra("name");


        }
    }

    private void setName(String folderName) {
        Log.d(TAG, "setName: setting the name of the folder to widgets");

        TextView name = findViewById(R.id.info_text); //change info_text to something in folder_file.xml MUST DO
        name.setText(folderName);
    }
}
