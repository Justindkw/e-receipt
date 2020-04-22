package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;

public class homeScreen extends AppCompatActivity implements recyclerViewAdapter.OnFolderListener {

//public class homeScreen extends AppCompatActivity implements recyclerViewAdapter.ButtonOnClick{//implements recyclerViewAdapter.ItemClickListener {

//    recyclerViewAdapter adapter;

    //Lucas' stuff starts here
    private ArrayList<String> mNames = new ArrayList<>();
    //Lucas' stuff ends here

    //Justin's stuff starts here
    private int curButtonPos = 0;
    private TableRow curRow;
    private TableLayout folderLayout;
    static final int REQUEST_FOLDER = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        folderNames(); //initializes anything in folderNames()

        //everything here should be removed after recyclerview is done
        curRow = new TableRow(this);
        folderLayout = findViewById(R.id.folderTable);
        folderLayout.addView(curRow);
        //up to here

        inflateFolders(); //adds test folders. Will remove after testing


        findViewById(R.id.createFolder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFolder();
            }
        });
        restoreFolders();

//Lucas's stuff starts here

//        ArrayList<recyclerViewData> setData = initFolders();
//
//        this.data = (RecyclerView) findViewById(R.id.rvNumbers);
//        int numberOfColumns = 2;
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, numberOfColumns);
//        this.data.setLayoutManager(mLayoutManager);
//
//        adapter = new recyclerViewAdapter(setData);
//        this.data.setAdapter(adapter);

        //Buttons down below
        findViewById(R.id.statisticsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on statisticsButton
                toStatsScreen();
            }
        });

        findViewById(R.id.budgetingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on budgetingButton
                toBudgetScreen();
            }
        });

    }

//    private ArrayList<recyclerViewAdapter> initFolders() {
//        ArrayList<recyclerViewAdapter> list = new ArrayList<>();
//
//        list.add(new recyclerViewAdapter("Food"));
//        list.add(new recyclerViewAdapter("Clothing"));
//
//        return list;
//    }

    private void folderNames() {
        mNames.add("Food");
        mNames.add("Clothing");
        mNames.add("merch");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        //recyclerViewAdapter adapter = new recyclerViewAdapter(this, mNames, this);//buttononclick keeps giving errors

        int numberOfColumns = 2;

        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

    }

    //Justin's stuff starts here
    private void inflateFolders(){
        for(int i = 0; i<5;i++){
            GlobalFolderList.add("folder "+i,new Folder("folder "+i,"",""));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK) {
            String name = data.getStringExtra("newFolder");
            if(name != null){
                insertButton(name);
            }
        }
    }//Justin's stuff ends here

//probably don't need this (see below)
//    @Override
//    public void onItemClick(View view, int position) {
//        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
//    }

//    private final OnClickListener mOnClickListener = new MyOnClickListener();
//    @Override
//    public MyViewHolder onCreateViewHolder(final ViewGrojup parent, final int viewType) {
//        View view =
//    }
//Lucas's stuff ends here

//Justin's stuff starts here
    public void restoreFolders(){
        for(HashMap.Entry<String,Folder> folder:GlobalFolderList.getFolderList().entrySet()){
            insertButton(folder.getKey());
        }
    }
    public void insertButton(String name){
        if(curButtonPos%2==0){
            //determines the position of the button
            curRow = new TableRow(this);
            folderLayout.addView(curRow);
            //creates and adds a new table row once the previous table row is filled with button
        }

        ConstraintLayout layout = new ConstraintLayout(this);
        ConstraintSet set = new ConstraintSet();
        layout.setMinWidth((int)convertDpToPixel((float)180));
        layout.setMinHeight((int)convertDpToPixel((float)250));

        layout.setMaxWidth((int)convertDpToPixel((float)180));
        layout.setMaxHeight((int)convertDpToPixel((float)250));
        set.clone(layout);
        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(R.drawable.bx_bx_receipt);
        Button b = new Button(this);
        b.setBackgroundResource(R.drawable.grey_horizontal_rectangle);
        b.setText(name);
        b.setTag(name);
        b.setWidth((int)convertDpToPixel((float)180));
        b.setHeight((int)convertDpToPixel((float)250));
        //creates button and its text and tag
        final Intent intent = new Intent(this, receiptFolder.class);
        //Log.d("HOMESCREEN",name);
        intent.putExtra("folderName", name);
        //saves folder to the next activity
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                //goes to the folder file activity
            }
        });
        layout.addView(image);
        layout.addView(b);
        curRow.addView(layout);
        curButtonPos++;
    }

    //@Override
//    public void ButtonOnClick(String string) {
//        Log.d("ran","yep");
//        startActivity(new Intent(this,receiptFolder.class).putExtra("folderName",string));
//    }

    private void toAddFolder(){
        startActivityForResult(new Intent(this,addFolder.class), REQUEST_FOLDER);
    }
//Justin's stuff ends here
//Lucas's stuff starts here
    //Buttons
    //Private button voids
    public float convertDpToPixel(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private void toStatsScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, Budgeting.class);
        startActivity(toBudgetScreen);
    }

    @Override
    public void onFolderClick(int position) {
        mNames.get(position);
        startActivity(new Intent(homeScreen.this, receiptFolder.class).putExtra("name", mNames.get(position)));
    }
    //Lucas' stuff ends here

}
