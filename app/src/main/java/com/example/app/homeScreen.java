package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

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
