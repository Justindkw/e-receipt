package com.example.app.StatisticStuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.BudgetingStuff.Budgeting;
import com.example.app.FolderStuff.FolderScreen;
import com.example.app.GlobalFolderList;
import com.example.app.R;

public class Statistics extends AppCompatActivity implements StatisticAdapter.AddButtonDestination {
//Justin's stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initRecyclerView();
        initTotalProgress();

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
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


    public void AddButtonDestination(String string) {
        startActivityForResult(new Intent(this, FolderStatistics.class).putExtra("folderName",string),4);
    }

    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.folderBudgetProgress);
        recyclerView.setAdapter(new StatisticAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }
    private void initTotalProgress(){
        int totalPercent = (int)(GlobalFolderList.getTotalSpending()/GlobalFolderList.getTotalBudget()*100);
        Log.d("totPercent",String.valueOf(totalPercent));
        ((CircularProgressBar)findViewById(R.id.totalBudgetBar)).setProgress(totalPercent);
    }

    //Buttons
    Button homeButton;
    Button budgetButton;

    //Private button voids
    private void toHomeScreen() {
        Intent toHomeScreen = new Intent(this, FolderScreen.class);
        startActivity(toHomeScreen);
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, Budgeting.class);
        startActivity(toBudgetScreen);
    }
}
