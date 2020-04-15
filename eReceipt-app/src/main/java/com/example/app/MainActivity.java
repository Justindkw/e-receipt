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
            }
        });
    }

//Nicolas's stuff starts here
    Button myButton;

    //private method

    int variableOne = 0; //various variables for what the chart will contain. can be changed
    int variableTwo = 0;


//    public void addVar1(View v) {
//        // Get the new value from a user input:
//        EditText var1EditText = findViewById(R.id.var1);
//
//        // Update the old value:
//        calsVar1 = Integer.parseInt(var1EditText.getText().toString());
//        updateChart();
//    }
//
//    public void addConsumed(View v) {
//        // Get the new value from a user input:
//        EditText var2EditText = findViewById(R.id.var2);
//
//        // Update the old value:
//        var2 = Integer.parseInt(consumedEditText.getText().toString());
//        updateChart();
//    }
//
//    private void updateChart(){
//        // Update the text in a center of the chart:
//        TextView numberOfItem = findViewById(R.id.number_of_item);
//        item.setText(String.valueOf(calsVar1) + " / " + calsVar2);
//
//        // Calculate the slice size and update the pie chart:
//        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
//        double d = (double) calsVar1 / (double) calsVar2;
//        int progress = (int) (d * 100);
//        pieChart.setProgress(progress);
//    }
//Nicolas's stuff ends here
}

