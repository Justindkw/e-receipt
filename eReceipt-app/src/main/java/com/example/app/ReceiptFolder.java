package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;


public class ReceiptFolder extends AppCompatActivity {
//Justin's stuff starts here
    //int to compare if it is our request
    static final int REQUEST_RECIEPT = 1;
    //feel free to delete Lucas
    private static  final String TAG = "folderFile"; //Lucas' stuff
    //folder name
    private String folderName;
    //receipts in folder
    private ArrayList<Receipt> receipts;
    //THE folder
    private Folder folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        //gets name of the folder from intent
        folderName = getIntent().getStringExtra("folderName");
        //find the folder by using it's name
        folder = GlobalFolderList.get(folderName);
        //gets receipts from folder
        receipts = folder.getReceipts();
        //sets button functions
        findViewById(R.id.createReceipt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddReceipt();
            }
        });
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHomeScreen();
            }
        });

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

        findViewById(R.id.budgetingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBudgetScreen();
            }
        });

    }
    @Override
    //after a new receipt is added
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECIEPT && resultCode == RESULT_OK) {
            int pos = data.getIntExtra("receiptPos",-1);
            if(pos != -1){
                //checks if results are ok and then updates the recycler view (WIP)
                Receipt newReceipt = receipts.get(folder.size()-1);
            }
        }
    }
//Justin's stuff ends here
    //sets button desinations
    private void toAddReceipt(){
        startActivityForResult(new Intent(ReceiptFolder.this, AddReceipt.class).putExtra("folderName", folderName), REQUEST_RECIEPT);
    }
//Lucas's stuff starts here
    private void toHomeScreen() {
        startActivity(new Intent(this, HomeScreen.class));
    }

    private void toStatsScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toBudgetScreen() {
        startActivity(new Intent(this, Budgeting.class));
    }

    //Lucas' stuff ends here
}
