package com.example.app.FolderStuff;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import java.util.Random;

public class AddFolder extends AppCompatActivity {
    private int mColor;
    boolean checked = false;
    EditText budgetEdit;
    TextView moneySymbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        budgetEdit = findViewById(R.id.setBudget);
        moneySymbol = findViewById(R.id.budgetMoneySymbol);
        ((Switch)findViewById(R.id.enableBudget)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
                if(isChecked){
                    moneySymbol.animate().alpha(1.0f).setDuration(200);
                    budgetEdit.animate().alpha(1.0f).setDuration(200);
                    budgetEdit.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }
                else{
                    moneySymbol.animate().alpha(0.0f).setDuration(200);
                    budgetEdit.animate().alpha(0.0f).setDuration(200);
                    budgetEdit.setInputType(0);
                }
            }
        });

        HSLColorPicker colorPicker = (HSLColorPicker) findViewById(R.id.colorPicker);
        Random rnd = new Random();
        mColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        colorPicker.setColor(mColor);
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                mColor = color;
            }
        });

        //sets the function of submit button
        findViewById(R.id.doneFolderButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.name)).getText().toString();
                double budget = Double.valueOf(((EditText)findViewById(R.id.setBudget)).getText().toString());
                //adds the newly made folder to the folderList
                if(checked){
                    GlobalFolderList.add(name, new Folder(name, mColor, true,budget));
                }
                else{
                    GlobalFolderList.add(name,new Folder(name, mColor));
                }
                //returns to HomeScreen with the new folder folderName
                setResult(RESULT_OK, new Intent().putExtra("newFolderName", name));
                finish();
            }
        });

        findViewById(R.id.backButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
