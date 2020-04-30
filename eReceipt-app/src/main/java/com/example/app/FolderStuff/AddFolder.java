package com.example.app.FolderStuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GlobalFolderList;
import com.example.app.R;

public class AddFolder extends AppCompatActivity {
//Justin's stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);
        //sets the function of submit button
        ((Button)findViewById(R.id.doneFolderButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.name)).getText().toString();
                //adds the newly made folder to the folderList
                GlobalFolderList.add(name,new Folder(name, ((EditText)findViewById(R.id.use)).getText().toString(), ((EditText)findViewById(R.id.notes)).getText().toString()));
                //returns to HomeScreen with the new folder folderName
                setResult(RESULT_OK,new Intent().putExtra("newFolderName",name));
                finish();
            }
        });
    }
}
