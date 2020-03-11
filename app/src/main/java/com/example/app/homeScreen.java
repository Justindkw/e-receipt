package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.HashMap;

public class homeScreen extends AppCompatActivity {

    int curButtonPos = 0;
    TableRow curRow;
    TableLayout folderLayout;
    Button createFolder;
    HashMap<String,Folder> folders = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        curRow = new TableRow(this);
        folderLayout = findViewById(R.id.folderTable);
        folderLayout.addView(curRow);
        createFolder = findViewById(R.id.createFolder);
        createFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertButton("Test");
                //User taps button

            }
        });
        statisticsButton = findViewById(R.id.statisticsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on statisticsButton
                toStatsScreen();
            }
        });

        budgetButton = findViewById(R.id.budgetingButton);
        budgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBudgetScreen();
            }
        });
    }

    public void addFolder(Folder folder){
        folders.put(folder.toString(),folder);
        insertButton(folder.toString());
    }
    public void insertButton(String s){
        if(curButtonPos%2==0){
            curRow = new TableRow(this);
            folderLayout.addView(curRow);
        }
        Button b = new Button(this);
        b.setText(s);
        b.setTag(s);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        curRow.addView(b);
        curButtonPos++;

    }

    //Buttons

    Button statisticsButton;
    Button budgetButton;

    //Private button voids
    private void toStatsScreen() {
        Intent toStatsScreen = new Intent(this, statistics.class);
        startActivity(toStatsScreen);
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, budgeting.class);
        startActivity(toBudgetScreen);
    }
}
