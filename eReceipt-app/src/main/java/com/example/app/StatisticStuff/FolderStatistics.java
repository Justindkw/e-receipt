package com.example.app.StatisticStuff;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.text.DecimalFormat;

public class FolderStatistics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_statistics);
        String name = getIntent().getStringExtra("folderName");
        ((TextView)findViewById(R.id.statsFolderName)).setText(name);
        Folder fold = GlobalFolderList.get(name);
        int color = getIntent().getIntExtra("color",0);

        findViewById(R.id.statsColor).setBackgroundColor(color);

        ((TextView)findViewById(R.id.statsBudget)).setText(new DecimalFormat("0.00").format(fold.getBudget()));

        ((TextView)findViewById(R.id.statsSpent)).setText(new DecimalFormat("0.00").format(fold.getSpending()));

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CircularProgressBar bar = findViewById(R.id.statsProgressBar);
        bar.setProgressColor(color);
        bar.setStrokeWidth(100);
        bar.setProgress((int)(fold.getSpending()/fold.getBudget()*100));
    }

}
