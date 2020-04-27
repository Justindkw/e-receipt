package com.example.app.HomeStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.GlobalFolderList;
import com.example.app.R;

public class AddFolder extends AppCompatActivity {
//Justin's stuff
    //xml stuff
    EditText nameInput;
    EditText useInput;
    EditText notesInput;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);
        //finds texts and button from xml
        nameInput = (EditText) findViewById(R.id.name);
        useInput = (EditText) findViewById(R.id.use);
        notesInput = (EditText) findViewById(R.id.notes);
        submitButton = (Button) findViewById(R.id.doneButton);
        //sets the function of submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                //adds the newly made folder to the folderList
                GlobalFolderList.add(name,new Folder(name, useInput.getText().toString(), notesInput.getText().toString()));
                //returns to HomeScreen with the new folder folderName
                setResult(RESULT_OK,new Intent().putExtra("newFolderName",name));
                finish();
            }
        });
    }
}
