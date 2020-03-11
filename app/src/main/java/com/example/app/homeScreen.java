package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class homeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        createFolder = findViewById(R.id.createFolder);
        createFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User taps button

            }
        });
    }
    int buttonRowPos = 0;
    Button createFolder;
    HashMap<String,Folder> folders = new HashMap<>();

    public void addFolder(Folder folder){
        folders.put(folder.toString(),folder);
        insertButton(folder.toString());
    }
    public void insertButton(String s){
        Button b = new Button(this);
        TableLayout folderLayout = (TableLayout)findViewById(R.id.folderTable);
        b.setText(s);
        b.setTag(s);
        b.

    }
}
