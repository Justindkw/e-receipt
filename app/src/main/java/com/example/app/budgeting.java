package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class budgeting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);

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
    }

    //Buttons
    Button homeButton;
    Button statisticsButton;

    //Private button voids
    private void toHomeScreen() {
        Intent toHomeScreen = new Intent(this, homeScreen.class);
        startActivity(toHomeScreen);
    }

    private void toStatsScreen() {
        Intent toStatsScreen = new Intent(this, homeScreen.class);
        startActivity(toStatsScreen);
    }
}
