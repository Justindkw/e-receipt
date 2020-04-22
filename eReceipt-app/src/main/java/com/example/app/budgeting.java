package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.HashMap;

public class budgeting extends AppCompatActivity {

    //Buttons
    Button homeButton;
    Button statisticsButton;
    Button backButton;
    Button submitButton;

    private Button btn;
    private RecyclerView recyclerView;
    private BudgetingAdapter budgetingAdapter;
    public ArrayList<Folder> budgetArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);

        recyclerView = (RecyclerView) findViewById(R.id.budgetRecycler);
        btn = (Button) findViewById(R.id.doneBudgetButton);

        budgetArrayList = new ArrayList<Folder>(GlobalFolderList.getFolderList().values());
        budgetingAdapter = new BudgetingAdapter(this,budgetArrayList);
        recyclerView.setAdapter(budgetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what happens when button is CLICKEDEDED
            }
        });





        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });

        statisticsButton = findViewById(R.id.statisticsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toStatsScreen();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });
    }
    private ArrayList<Budget> populateList(){

        ArrayList<Budget> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            Budget budget = new Budget();
            budget.setEditTextValue(String.valueOf(i));
            list.add(budget);
        }

        return list;
    }

    //Private button voids
    private void toHomeScreen() {
        Intent toHomeScreen = new Intent(this, homeScreen.class);
        startActivity(toHomeScreen);
    }

    private void toStatsScreen() {
        Intent toStatsScreen = new Intent(this, MainActivity.class);
        startActivity(toStatsScreen);
    }
}
