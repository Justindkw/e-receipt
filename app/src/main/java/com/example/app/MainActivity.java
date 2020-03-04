package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.cameraButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps button
                navigateToSecondScreen();
            }
        });
    }

    Button myButton;

    //private method
    private void navigateToSecondScreen() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    int calsBurned = 0;
    int calsConsumed = 0;

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
   // }


    public void addBurned(View v) {
        // Get the new value from a user input:
        EditText burnedEditText = findViewById(R.id.burned);

        // Update the old value:
        calsBurned = Integer.parseInt(burnedEditText.getText().toString());
        updateChart();
    }

    public void addConsumed(View v) {
        // Get the new value from a user input:
        EditText consumedEditText = findViewById(R.id.consumed);

        // Update the old value:
        calsConsumed = Integer.parseInt(consumedEditText.getText().toString());
        updateChart();
    }

    private void updateChart(){
        // Update the text in a center of the chart:
        TextView numberOfCals = findViewById(R.id.number_of_calories);
        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);

        // Calculate the slice size and update the pie chart:
        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
        double d = (double) calsBurned / (double) calsConsumed;
        int progress = (int) (d * 100);
        pieChart.setProgress(progress);
    }
}

