package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements HomeScreenAdapter.AddButtonDestination {//implements recyclerViewAdapter.ItemClickListener {
//Justin's stuff starts here
    //recycler adapter
    private HomeScreenAdapter adapter;
    //int to compare if it is our request
    static final int REQUEST_FOLDER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //adds buncha folders for sake of demo and debugging
        inflateFolders();
        //creates recycler view
        initRecyclerView();
        //sets button functions
        findViewById(R.id.createFolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFolder();
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
    private void inflateFolders(){
        for(int i = 0; i<5;i++){
            GlobalFolderList.add("folder "+i,new Folder("folder "+i,"",""));
        }
    }
    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);

        adapter = new HomeScreenAdapter(new ArrayList<String>(GlobalFolderList.getFolderList().keySet()),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    //after a new folder is added
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newFolderName");
            if(name != null){
                //checks if results are ok and then updates the recycler view
                adapter.addItem(name);
            }
        }
    }

    //this is used to set button function for all the buttons in recycler view
    public void AddButtonDestination(String string) {
        startActivity(new Intent(this, ReceiptFolder.class).putExtra("folderName",string));
    }
    //functions to start button activities DUDDDDE
    private void toAddFolder(){
        startActivityForResult(new Intent(this, AddFolder.class), REQUEST_FOLDER);
    }
//Justin's stuff ends here
//Lucas's stuff starts here
    private void toStatsScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toBudgetScreen() {
        startActivity(new Intent(this, Budgeting.class));
    }
//Lucas' stuff ends here
}
