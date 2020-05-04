package com.example.app.StatisticStuff;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.text.DecimalFormat;

public class FolderStatistics extends AppCompatActivity {
    Button backButton;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_statistics);
        name = getIntent().getStringExtra("folderName");
        ((TextView)findViewById(R.id.statsFolderName)).setText(name);
        Folder fold = GlobalFolderList.get(name);
        ((TextView)findViewById(R.id.statsBudget)).setText(new DecimalFormat("0.00").format(fold.getBudget()));
        ((TextView)findViewById(R.id.statsSpent)).setText(new DecimalFormat("0.00").format(fold.getSpending()));
        ((CircularProgressBar)findViewById(R.id.statsProgressBar)).setProgress((int)(fold.getSpending()/fold.getBudget()*100));

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
