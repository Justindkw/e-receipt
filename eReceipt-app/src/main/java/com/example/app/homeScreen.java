package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.TableRow.LayoutParams;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class homeScreen extends AppCompatActivity {//implements recyclerViewAdapter.ItemClickListener {

//    recyclerViewAdapter adapter;

    //Justin's stuff starts here
    private Folder newFolder;
    private int curButtonPos = 0;
    private TableRow curRow;
    private TableLayout folderLayout;
    private Button createFolder;
    private static HashMap<String,Folder> folders = new HashMap<>();

    //Lucas' stuff starts here
    private RecyclerView data;
    private RecyclerView.Adapter adapter;
    //Lucas' stuff ends here

    static final int REQUEST_FOLDER = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        curRow = new TableRow(this);
        folderLayout = findViewById(R.id.folderTable);
        folderLayout.addView(curRow);

        createFolder = findViewById(R.id.createFolder);
        createFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddFolder();
            }

        });

//Lucas's stuff starts here

        ArrayList<recyclerViewData> setData = initFolders();

        this.data = (RecyclerView) findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, numberOfColumns);
        this.data.setLayoutManager(mLayoutManager);

        adapter = new recyclerViewAdapter(setData);
        this.data.setAdapter(adapter);


        // data to populate the RecyclerView with
//        String[] data = {"Food", "Clothing", "Gas", "Entertainment", "5"};
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
//        int numberOfColumns = 2;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//        adapter = new recyclerViewAdapter(this, data);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);

//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
//        adapter = new recyclerViewAdapter(this, folderNames);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);



        //Buttons down below
        statisticsButton = findViewById(R.id.statisticsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps on statisticsButton
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

        foodButton = findViewById(R.id.Food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFoodFolder();
            }
        });

        clothingButton = findViewById(R.id.Clothing);
        clothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toClothingFolder();
            }
        });

    }

    private ArrayList<recyclerViewData> initFolders() {
        ArrayList<recyclerViewData> list = new ArrayList<>();

        list.add(new recyclerViewData("Food", R.id.Food));
        list.add(new recyclerViewData("Clothing", R.id.Clothing));

        return list;
    }

    public static HashMap<String, Folder> getFolders() {
        return folders;
    }

    //Justin's stuff starts here
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK) {
            newFolder = data.getParcelableExtra("newFolder");
            if(newFolder != null){
                folders.put(newFolder.toString(),newFolder);
                insertButton(newFolder.toString());
                System.out.println("newfolder added");
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
//    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
//        View view =
//    }
//Lucas's stuff ends here

//Justin's stuff starts here

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
        final Intent intent = new Intent(this, FolderFile.class);
        intent.putExtra("folder",folders.get(name));
        //saves folder to the next activity
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                //goes to the folder file activity
            }
        });
        //sets onClick for the new button (which sends information to the new activity
        layout.addView(image);
        layout.addView(b);
        //adds image and button to the constraint layout
        curRow.addView(layout);
        //adds the constraint view to the actual layout
        curButtonPos++;
        //updates button position
    }

//Justin's stuff ends here

//Lucas's stuff starts here
    //Buttons
    Button statisticsButton;
    Button budgetButton;
    Button foodButton;
    Button clothingButton;

    //Private button voids
    public float convertDpToPixel(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private void toStatsScreen() {
        Intent toStatsScreen = new Intent(this, addReceipt.class);
        startActivity(toStatsScreen);
    }

    private void toBudgetScreen() {
        Intent toBudgetScreen = new Intent(this, budgeting.class);
        startActivity(toBudgetScreen);
    }

    private void toFoodFolder() {
        Intent toFoodFolder = new Intent(this, foodFolder.class);
        startActivity(toFoodFolder);
    }

    private void toClothingFolder() {
        Intent toClothingFolder = new Intent(this, clothingFolder.class);
        startActivity(toClothingFolder);
    }
    //Lucas' stuff ends here
    private void toAddFolder(){
        startActivityForResult(new Intent(this,addFolder.class), REQUEST_FOLDER);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("folders",folders);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        HashMap<String, Folder> savedState = (HashMap) savedInstanceState.getSerializable("folders");
        if (savedState == null) {
            System.out.println("nothing lol");
        } else {
            folders = savedState;
        }
    }
}
