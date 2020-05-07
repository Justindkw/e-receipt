package com.example.app.ReceiptStuff;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Extras;
import com.example.app.FolderStuff.Folder;
import com.example.app.FolderStuff.FolderScreen;
import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.example.app.StatisticStuff.FolderStatistics;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ReceiptScreen extends AppCompatActivity implements ReceiptScreenAdapter.AddButtonDestination{
//Justin's stuff starts here
    //int to compare if it is our request
    static final int REQUEST_RECEIPT = 1;
    static final int REQUEST_POPUP = 5;
    //feel free to delete Lucas
    private static  final String TAG = "receiptFile"; //Lucas' stuff
    //folder name
    private String folderName;
    //receipts in folder
    private ArrayList<Receipt> receipts;
    //THE folder
    private Folder folder;
    //recycler adapter
    private ReceiptScreenAdapter adapter;

    private TextView selectText;

    private ImageButton deleteButton;
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
        //finds selectText from xml
        selectText = findViewById(R.id.selectText);
        initSorter();
        //sets the name of the folder in the activity
        ((TextView)findViewById(R.id.folderName)).setText(folderName);
        //sets the quick stats of the folder
        TextView quickStats = findViewById(R.id.quickReceiptStats);
        ImageButton toStats = findViewById(R.id.statFolder);
        if(folder.isBudgetable()) {
            quickStats.setText("$" + new DecimalFormat("0.00").format(folder.getSpending()) + "/$" + new DecimalFormat("0.00").format(folder.getBudget()));
            toStats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statsFolder();
                }
            });
        }
        else{
            quickStats.setText("$" + new DecimalFormat("0.00").format(folder.getSpending()));
            toStats.setAlpha(0.0f);
        }
        quickStats.setTextColor(folder.getColor());

        ((TextView)findViewById(R.id.receiptText)).setTextColor(folder.getColor());

        findViewById(R.id.folderColor).setBackgroundColor(folder.getColor());
        //creates recycler view
        initRecyclerView();
        //sets button functions
        deleteButton = findViewById(R.id.receiptDelete);
        deleteButton.getBackground().mutate().setColorFilter(folder.getColor(), PorterDuff.Mode.SRC_ATOP);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDeleteMode();
            }
        });
        ImageButton createReceipt = findViewById(R.id.createReceipt);
        createReceipt.getBackground().mutate().setColorFilter(folder.getColor(), PorterDuff.Mode.SRC_ATOP);
        createReceipt.setOnClickListener(new View.OnClickListener() {
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

    }
    //initializes recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.receiptRecyclerView);

        adapter = new ReceiptScreenAdapter(receipts, this);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

    }
    private void initSorter(){
        Spinner sorter = findViewById(R.id.receiptSorter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Date","Company","Cost","Refund due"}){
            Typeface font = ResourcesCompat.getFont(getContext(),R.font.ralewayregular);
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView v = (TextView)super.getView(position, convertView, parent);
                v.setTypeface(font);
                v.setTextColor(Color.BLACK);
                v.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView v = (TextView)super.getDropDownView(position, convertView, parent);
                v.setTypeface(font);
                v.setTextColor(Color.BLACK);
                v.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                return v;
            }
        };
        sorter.setAdapter(adapter);
        sorter.setSelection(folder.getSortType());
        sorter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                folder.setSortType(position);
                Extras.sort(receipts,position);
                notifyChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void notifyChange(){
        adapter.notifyDataSetChanged();
    }

    private void toggleDeleteMode(){
        if(adapter.getDeleteMode()){
            deleteButton.setBackgroundResource(R.drawable.select_black);
            deleteButton.getBackground().mutate().setColorFilter(folder.getColor(), PorterDuff.Mode.SRC_ATOP);
            adapter.deleteSelected();
            selectText.setAlpha(1.0f);
            adapter.setDeleteMode(false);
        }
        else{
            deleteButton.setBackgroundResource(R.drawable.delete);
            selectText.setAlpha(0.0f);
            adapter.setDeleteMode(true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    //after a new receipt is added
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECEIPT && resultCode == RESULT_OK) {
            if(data.getIntExtra("receiptPos",-1) != -1){
                adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == REQUEST_POPUP && resultCode == RESULT_OK){
            int pos = data.getIntExtra("receiptPosition",-1);
            if(pos != -1 && !data.getBooleanExtra("timer",true)){
                receipts.get(pos).setTimer(false);
                adapter.notifyItemChanged(pos);
            }

        }
    }
//Justin's stuff ends here
    public void AddReceiptDestination(int receipt) {
        startActivityForResult(new Intent(this, ReceiptPopUp.class).putExtra("folder",folderName).putExtra("receipt",receipt),REQUEST_POPUP);
    }
    //sets button destinations
    private void toAddReceipt() {
        startActivityForResult(new Intent(ReceiptScreen.this, AddReceipt.class).putExtra("folderName", folderName), REQUEST_RECEIPT);
        //sets button destinations
    }
//Lucas's stuff starts here
    private void toHomeScreen() {
        startActivity(new Intent(this, FolderScreen.class));
    }

    private void statsFolder() {
        startActivityForResult(new Intent(this, FolderStatistics.class).putExtra("folderName",folderName).putExtra("color",folder.getColor()), 4);
    }
    //Lucas' stuff ends here
}

