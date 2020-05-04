package com.example.app.FolderStuff;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.BudgetingStuff.Budgeting;
import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.example.app.ReceiptStuff.Receipt;
import com.example.app.ReceiptStuff.ReceiptScreen;
import com.example.app.StatisticStuff.Statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

//import com.example.app.ReceiptStuff.ReceiptFolder;

//FolderScreen is the HOME SCREEN

//FolderScreen is the HOME SCREEN

public class FolderScreen extends AppCompatActivity implements FolderScreenAdapter.AddButtonDestination {//implements recyclerViewAdapter.ItemClickListener {
//Justin's stuff starts here
    //recycler adapter
    private FolderScreenAdapter adapter;
    private ImageButton deleteButton;
    //int to compare if it is our request
    static final int REQUEST_FOLDER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_screen);
        //adds buncha folders for sake of demo and debugging
        if(GlobalFolderList.initator.get()){
            GlobalFolderList.initator.set(false);
            inflateFolders();
        }
        //creates recycler view
        initRecyclerView();
        deleteButton = findViewById(R.id.folderDelete);
        //sets button functions
        findViewById(R.id.createFolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFolder();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDeleteMode();
            }
        });
//Justin's stuff ends here
//Lucas's stuff starts here

        //Buttons down below
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
//Lucas's stuff ends here
//Justin's stuff starts here
    //make buncha folders

    private void inflateFolders() {
        Bitmap defaultPic = BitmapFactory.decodeResource(getResources(), R.drawable.receipt);
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            Folder fold = new Folder("folder " + i, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), 0.00);
            Log.d("color",fold.getColor()+" ");
            fold.setBudget(100 +Math.random() * 300);
            fold.setSpending(50 + Math.random() * 200);
            for(int s = 0;s<(int)(Math.random()*3)+2;s++){
                try {
                    fold.addReceipt(new Receipt(defaultPic,
                            Math.round(Math.random()*20)/20.0,
                            "Company "+s,
                            new SimpleDateFormat("dd/MM/yyyy").parse(Math.round(Math.random()*30)+"/05/2020"),
                            Math.random()>0.5)
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            GlobalFolderList.add("folder " + i, fold);
        }
    }

    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);

        adapter = new FolderScreenAdapter(new ArrayList<String>(GlobalFolderList.getFolderList().keySet()),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }
    private void toggleDeleteMode(){
        if(adapter.getDeleteMode()){
            deleteButton.setBackgroundResource(R.drawable.select_button);
            adapter.deleteSelected();
            adapter.setDeleteMode(false);
        }
        else{
            deleteButton.setBackgroundResource(R.drawable.delete);
            adapter.setDeleteMode(true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    //after a new folder is added
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newFolderName");
            if(name != null){
                Log.d("name","added"+name);
                //checks if results are ok and then updates the recycler view
                adapter.addItem(name);
            }
        }
    }

    //this is used to set button function for all the buttons in recycler view
    public void AddButtonDestination(String string) {
        startActivity(new Intent(this, ReceiptScreen.class).putExtra("folderName",string));
    }
    //functions to start button activities DUDDDDE
    private void toAddFolder(){
        //startActivityForResult(new Intent(this, AddFolder.class), REQUEST_FOLDER);
        startActivityForResult(new Intent(this, AddFolder.class), REQUEST_FOLDER);
    }
//Justin's stuff ends here
//Lucas's stuff starts here
    private void toStatsScreen() {
        startActivity(new Intent(this, Statistics.class));
    }

    private void toBudgetScreen() {
        startActivity(new Intent(this, Budgeting.class));
    }
//Lucas' stuff ends here
}
