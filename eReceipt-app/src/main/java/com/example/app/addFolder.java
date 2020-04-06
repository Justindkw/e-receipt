package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addFolder extends AppCompatActivity {

    Folder folder;

    EditText nameInput;
    EditText useInput;
    EditText notesInput;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        nameInput = (EditText) findViewById(R.id.name);
        useInput = (EditText) findViewById(R.id.use);
        notesInput = (EditText) findViewById(R.id.notes);

        submitButton = (Button) findViewById(R.id.doneButton);
        final Intent intent = new Intent(this, homeScreen.class);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folder = new Folder(nameInput.getText().toString(), useInput.getText().toString(), notesInput.getText().toString());
                intent.putExtra("newFolder",folder);
                startActivity(intent);
            }
        });
    }
}
