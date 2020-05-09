package com.example.app.StatisticStuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.BudgetingStuff.Budgeting;
import com.example.app.FolderStuff.FolderScreen;
import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.text.DecimalFormat;

public class Statistics extends AppCompatActivity implements StatisticAdapter.AddButtonDestination {
//Justin's stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initRecyclerView();
        initTotalProgress();

        findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });

        findViewById(R.id.budgetingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBudgetScreen();
            }
        });

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void AddButtonDestination(String string,int color) {
        startActivityForResult(new Intent(this, FolderStatistics.class).putExtra("folderName",string).putExtra("color",color),4);
    }

    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.folderBudgetProgress);
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
        recyclerView.setAdapter(new StatisticAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }
    private void initTotalProgress(){
        double totalBudget = GlobalFolderList.getTotalBudget();
        int totalPercent = (int)(GlobalFolderList.getTotalBudgetableSpending()/totalBudget*100);
        ((TextView)findViewById(R.id.budgetMoney)).setText(new DecimalFormat("0.00").format(totalBudget));
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
