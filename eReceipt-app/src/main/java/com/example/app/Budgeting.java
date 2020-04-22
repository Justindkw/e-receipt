package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Budgeting extends AppCompatActivity {

    //Buttons
    private Button homeButton;
    private Button statisticsButton;
    private Button backButton;
    private Button submitButton;

    private RecyclerView recyclerView;
    private BudgetingAdapter budgetingAdapter;
    public ArrayList<Folder> budgetArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);

        recyclerView = (RecyclerView) findViewById(R.id.budgetRecycler);
        submitButton = (Button) findViewById(R.id.doneBudgetButton);

        budgetArrayList = new ArrayList<Folder>(GlobalFolderList.getFolderList().values());
        budgetingAdapter = new BudgetingAdapter(this,budgetArrayList);
        recyclerView.setAdapter(budgetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BudgetingAdapter.correctBudgets()){
                    TextView totalBudgetText = findViewById(R.id.totalBudget);
                    totalBudgetText.setText(String.valueOf(BudgetingAdapter.finalizeBudgets()));
//                    for(Folder fold:GlobalFolderList.getFolderList().values()){
//                        Log.d(fold.toString(),fold.getTotalBudget()+"");
//                    }
                }
                else{
                    //this means the user input budget is not correct. For example id they don't have any value or it includes letters
                }
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
