package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FolderFile extends AppCompatActivity {

    private Folder folder; //not Lucas' stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_file);
        folder = getIntent().getParcelableExtra("folder"); //not Lucas' stuff
    }

}
