package com.example.app.BudgetingStuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app.FolderStuff.Folder;
import com.example.app.FolderStuff.GlobalFolderList;
import com.example.app.HomeStuff.HomeScreen;
import com.example.app.MainActivity;
import com.example.app.R;

import java.util.ArrayList;
public class Budgeting extends AppCompatActivity {

    BudgetingAdapter budgetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);
//Justin's stuff starts here
        //creates a List of folders from the folderList map
        //sets up the recycler view
        budgetingAdapter = new BudgetingAdapter(this,new ArrayList<Folder>(GlobalFolderList.getFolderList().values()));
        RecyclerView recyclerView = findViewById(R.id.budgetRecycler);
        recyclerView.setAdapter(budgetingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        //sets done budget button function
        findViewById(R.id.doneBudgetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if all user inputted budgets are doubles(numbers), then set budget and total budget according to user input
                if(budgetingAdapter.correctBudgets()){
                    budgetingAdapter.finalizeBudgets();
                    ((TextView)findViewById(R.id.totalBudget)).setText(String.valueOf(GlobalFolderList.getTotalBudget()));
                }
                else{
                    //this means the user input budget is not correct. For example if they don't have any value or it includes letters
                    //can add something here to warn user of their wrong input
                }
            }
        });
//Justin's stuff ends here
//Lucas's stuff starts here
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
                toHomeScreen();
            }
        });
    }

    private void toHomeScreen() {
        startActivity(new Intent(this, HomeScreen.class));
    }

    private void toStatsScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
