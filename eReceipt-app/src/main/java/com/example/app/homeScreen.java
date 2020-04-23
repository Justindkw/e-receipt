package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;

public class homeScreen extends AppCompatActivity implements HomeScreenAdapter.AddButtonDestination {//implements recyclerViewAdapter.ItemClickListener {
//Justin's stuff starts here
    private HomeScreenAdapter adapter;
    static final int REQUEST_FOLDER = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        inflateFolders();
        initRecyclerView(); //initializes anything in folderNames()

        findViewById(R.id.createFolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFolder();
            }
        });
//Justin's stuff ends here
//Lucas's stuff starts here

        //Buttons down below
        findViewById(R.id.statisticsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on statisticsButton
                toStatsScreen();
            }
        });

        findViewById(R.id.budgetingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on budgetingButton
                toBudgetScreen();
            }
        });

    }
//Lucas's stuff ends here
//Justin's stuff starts here
    private void initRecyclerView() { //THIS INITIALIZES THE RECYCLER VIEW
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);

        adapter = new HomeScreenAdapter(new ArrayList<String>(GlobalFolderList.getFolderList().keySet()),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void inflateFolders(){
        for(int i = 0; i<5;i++){
            GlobalFolderList.add("folder "+i,new Folder("folder "+i,"",""));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newFolder");
            if(name != null){
                adapter.addItem(name);
            }
        }
    }
    @Override
    public void AddButtonDestination(String string) {
        startActivity(new Intent(this,receiptFolder.class).putExtra("folderName",string));
    }

    private void toAddFolder(){
        startActivityForResult(new Intent(this,addFolder.class), REQUEST_FOLDER);
    }
//Justin's stuff ends here
//Lucas's stuff starts here
    //Buttons
    private void toStatsScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, Budgeting.class);
        startActivity(toBudgetScreen);
    }
//Lucas' stuff ends here

}
