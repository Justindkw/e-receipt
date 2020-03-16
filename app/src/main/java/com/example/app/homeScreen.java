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
    private int mData;
    private int curButtonPos = 0;
    private TableRow curRow;
    private TableLayout folderLayout;
    private Button createFolder;
    private HashMap<String,Folder> folders = new HashMap<>();

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
    public void insertButton(String name){
        if(curButtonPos%2==0){
            curRow = new TableRow(this);
            folderLayout.addView(curRow);
        }
        Button b = new Button(this);
        final Folder thisFolder = folders.get(name);
        Intent intent = new Intent(this,FolderFile.class);
        intent.putExtra("folder",thisFolder);
        b.setText(name);
        b.setTag(name);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFolderFile(folders.get(thisFolder));
            }
        });
        curRow.addView(b);
        curButtonPos++;

    }
    public void goToFolderFile(Folder folder){
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
