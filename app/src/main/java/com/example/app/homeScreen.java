package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.HashMap;

public class homeScreen extends AppCompatActivity {
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
        //adds a new folder to the folder map
        insertButton(folder.toString());
        //adds a new button to the activity
    }
    public void insertButton(String name){
        if(curButtonPos%2==0){
            //determines the position of the button
            curRow = new TableRow(this);
            folderLayout.addView(curRow);
            //creates and adds a new table row once the previous table row is filled with button
        }

        ConstraintLayout layout = new ConstraintLayout(this);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        //creates the new constraint layout and the layout setter
        //set is the one i need you to edit Lucas, because it determines the constraint, margin, etc of the button.
        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(R.drawable.bx_bx_receipt);
        //creates and sets new image view

//        ConstraintLayout.LayoutParams imageLayoutParams = (ConstraintLayout.LayoutParams) findViewById(R.id.image1).getLayoutParams();
//        image.setLayoutParams(imageLayoutParams);
//        ConstraintLayout.LayoutParams newLayoutParams = (ConstraintLayout.LayoutParams) findViewById(R.id.image1).getLayoutParams();
//        image.setLayoutParams(newLayoutParams);
        //this is intended to copy the param layout of the first button, but im not sure if it works for not
        //please fix lucas

        Button b = new Button(this);
        b.setBackgroundResource(R.drawable.grey_horizontal_rectangle);
        b.setText(name);
        b.setTag(name);
        //creates button and its text and tag
        final Intent intent = new Intent(this,FolderFile.class);
        intent.putExtra("folder",folders.get(name));
        //saves folder to the next activty
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                //goes to the folder file activity
            }
        });
        //sets onClick for the new button (which sends information to the new activity
        layout.addView(image);
        layout.addView(b);
        //adds image and button to the constraint layout
        set.connect(image.getId(), ConstraintSet.TOP, b.getId(), ConstraintSet.TOP, 60);
        set.applyTo(layout);
        //this is suppose to set the constaint/margins/etc to to the correct size, but i have not clue how to do that
        //the set code above is directly copied from site, so please change
        curRow.addView(layout);
        //adds the constraint view to the actual layout
        curButtonPos++;
        //updates button pos
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
