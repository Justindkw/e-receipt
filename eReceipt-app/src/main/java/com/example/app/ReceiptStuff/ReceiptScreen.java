package com.example.app.ReceiptStuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.BudgetingStuff.Budgeting;
import com.example.app.FolderStuff.FolderScreen;
import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.example.app.StatisticStuff.Statistics;

import java.util.ArrayList;


public class ReceiptScreen extends AppCompatActivity implements ReceiptScreenAdapter.AddButtonDestination{
//Justin's stuff starts here
    //int to compare if it is our request
    static final int REQUEST_RECEIPT = 1;
    //feel free to delete Lucas
    private static  final String TAG = "receiptFile"; //Lucas' stuff
    //folder name
    private String folderName;
    //receipts in folder
    private ArrayList<Receipt> receipts;
    //THE folder
    private Folder folder;
    ////recycler adapter
    private ReceiptScreenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        //gets name of the folder from intent
        folderName = getIntent().getStringExtra("folderName");
        //sets the name of the folder in the activity
        ((TextView)findViewById(R.id.folderName)).setText(folderName);
        //find the folder by using it's name
        folder = GlobalFolderList.get(folderName);
        //gets receipts from folder
        receipts = folder.getReceipts();
        //creates recycler view
        initRecyclerView();
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

//    //make buncha receipts
//    private void inflateReceipts(){
//        for(int i = 0; i<5;i++){
//            GlobalFolderList.add("folder "+i,new Folder("folder "+i,"",""));
//        }
//    }
    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.receiptRecyclerView);

        adapter = new ReceiptScreenAdapter(receipts, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    @Override
    //after a new receipt is added
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECEIPT && resultCode == RESULT_OK) {
            int pos = data.getIntExtra("receiptPos",-1);
            if(pos != -1){
                //checks if results are ok and then updates the recycler view (WIP)
                Receipt newReceipt = receipts.get(folder.size()-1);
                adapter.notifyInsert();
            }
        }
    }
//Justin's stuff ends here

    //THIS HAS NO CURRENT USEEEEEEE
    public void AddReceiptDestination(String string) {
        startActivity(new Intent(this, AddReceipt.class).putExtra("receiptName", string));
    }
    //sets button desinations
    private void toAddReceipt() {
        startActivityForResult(new Intent(ReceiptScreen.this, AddReceipt.class).putExtra("folderName", folderName), REQUEST_RECEIPT);
        //sets button destinations
    }
//Lucas's stuff starts here
    private void toHomeScreen() {
        startActivity(new Intent(this, FolderScreen.class));
    }

    private void toStatsScreen() {
        startActivity(new Intent(this, Statistics.class));
    }

    private void toBudgetScreen() {
        startActivity(new Intent(this, Budgeting.class));
    }

    //Lucas' stuff ends here
}

