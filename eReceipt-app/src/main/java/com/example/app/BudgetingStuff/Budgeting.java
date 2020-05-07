package com.example.app.BudgetingStuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.FolderStuff.FolderScreen;
import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.example.app.StatisticStuff.Statistics;

import java.text.DecimalFormat;

public class Budgeting extends AppCompatActivity {

    BudgetingAdapter budgetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);
        //sets up the recycler view
        initRecyclerView();
        //initializes the total budget number
        updateTotalBudget();
        //sets done budget button function
        findViewById(R.id.doneBudgetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if all user inputted budgets are doubles(numbers), then set budget and total budget according to user input
                if(budgetingAdapter.correctBudgets()){
                    budgetingAdapter.finalizeBudgets();
                    updateTotalBudget();
                }
                else{
                    //this means the user input budget is not correct. For example if they don't have any value or it includes letters
                    //can add something here to warn user of their wrong input
                }
            }
        });
        //sets the bottom three buttons' function to start their individual activities
        findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });
        findViewById(R.id.statisticsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toStatsScreen();
            }
        });

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //sets up the recycler view
    private void initRecyclerView(){
        budgetingAdapter = new BudgetingAdapter(this, GlobalFolderList.retrieveBudgetableFolders());
        RecyclerView recyclerView = findViewById(R.id.budgetRecycler);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.setAdapter(budgetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }
    private void updateTotalBudget(){
        ((TextView)findViewById(R.id.totalBudget)).setText(new DecimalFormat("0.00").format(GlobalFolderList.getTotalBudget()));
    }

    private void toHomeScreen() {
        startActivity(new Intent(this, FolderScreen.class));
    }

    private void toStatsScreen() {
        startActivity(new Intent(this, Statistics.class));
    }
}
