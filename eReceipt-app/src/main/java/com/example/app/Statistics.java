package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.BudgetingStuff.Budgeting;
import com.example.app.HomeStuff.HomeScreen;

public class Statistics extends AppCompatActivity {
//Lucas's stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

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

    //Buttons
    Button homeButton;
    Button budgetButton;

    //Private button voids
    private void toHomeScreen() {
        Intent toHomeScreen = new Intent(this, HomeScreen.class);
        startActivity(toHomeScreen);
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, Budgeting.class);
        startActivity(toBudgetScreen);
    }
}
