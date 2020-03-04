package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.butt);
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
}

