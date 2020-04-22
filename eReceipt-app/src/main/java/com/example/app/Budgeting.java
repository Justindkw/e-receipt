package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
public class Budgeting extends AppCompatActivity {

    public ArrayList<Folder> budgetArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);
//Justin's stuff starts here
        //sets up the recyclerview plus the list of folders
        budgetArrayList = new ArrayList<Folder>(GlobalFolderList.getFolderList().values());
        BudgetingAdapter budgetingAdapter = new BudgetingAdapter(this,budgetArrayList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.budgetRecycler);
        recyclerView.setAdapter(budgetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        //by clicking on the doneBudgetButton, the budget for the clas
        findViewById(R.id.doneBudgetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BudgetingAdapter.correctBudgets()){
                    BudgetingAdapter.finalizeBudgets();
                    ((TextView)findViewById(R.id.totalBudget)).setText(String.valueOf(GlobalFolderList.getTotalBudget()));
//                    for(Folder fold:GlobalFolderList.getFolderList().values()){
//                        Log.d(fold.toString(),fold.getBudget()+"");
//                    }
                }
                else{
                    //this means the user input budget is not correct. For example id they don't have any value or it includes letters
                }
            }
        });
//Justin's stuff ends here
//Lucas's stuff starts here
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
