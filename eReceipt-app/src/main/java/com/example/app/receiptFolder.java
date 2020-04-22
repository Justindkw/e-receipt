package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import android.widget.TextView;


public class receiptFolder extends AppCompatActivity {
    static final int REQUEST_RECIEPT = 1;

    private static  final String TAG = "folderFile"; //Lucas' stuff

    private String name;
    private ArrayList<Receipt> receipts;
    private Button createReciept;
    private ImageView test;
    private Folder folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        test = findViewById(R.id.testImage);
        name = getIntent().getStringExtra("folderName");
        folder = GlobalFolderList.get(name);
        Log.d("receipt",name);
        receipts = folder.getReceipts();
        createReciept = findViewById(R.id.createReceipt);
        createReciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddReceipt();
            }
        });
        Log.d(TAG, "onCreate: started.");
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });

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

        budgetButton = findViewById(R.id.budgetingButton);
        budgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on budgetingButton
                toBudgetScreen();
            }
        });

    }
    private void toAddReceipt(){
        Intent intent = new Intent(receiptFolder.this, addReceipt.class);
        Log.d("YOU SURE NAME IN EXTRA?",name);
        intent.putExtra("name",name);
        startActivityForResult(intent, REQUEST_RECIEPT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECIEPT && resultCode == RESULT_OK) {
            int pos = data.getIntExtra("receiptPos",-1);
            Log.d("app",receipts.size()+" "+pos);
            if(pos != -1){
                Receipt newReceipt = receipts.get(pos);
                test.setImageBitmap(newReceipt.getPhoto());
            }
        }
    }
    //Lucas' stuff starts here
    //This sets the folder's name depending on what folder is clicked in the RecyclerView
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents"); //Log.d is just to check if the method is working

        if(getIntent().hasExtra("name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String folderName = getIntent().getStringExtra("name");


        }
    }

    private void setName(String folderName) {
        Log.d(TAG, "setName: setting the name of the folder to widgets");

        TextView name = findViewById(R.id.folderName); //change info_text to something in folder_file.xml MUST DO
        name.setText(folderName);
    }

    //Buttons
    Button backButton;
    Button homeButton;
    Button statisticsButton;
    Button budgetButton;

    //Private button voids
    private void toHomeScreen() {
        Intent toHomeScreen = new Intent(this, homeScreen.class);
        startActivity(toHomeScreen);
    }

    private void toStatsScreen() {
        Intent toStatsScreen = new Intent(this, MainActivity.class);
        startActivity(toStatsScreen);
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, Budgeting.class);
        startActivity(toBudgetScreen);
    }

    //Lucas' stuff ends here
}
