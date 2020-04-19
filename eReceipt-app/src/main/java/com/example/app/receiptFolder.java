package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class receiptFolder extends AppCompatActivity {
    static final int REQUEST_RECIEPT = 1;
    //GlobalFolderList folderList = (GlobalFolderList)getApplication();

    private static  final String TAG = "folderFile"; //Lucas' stuff

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        Log.d(TAG, "onCreate: started.");
        name = getIntent().getParcelableExtra("folderName");
    }
    private void toAddFolder(){
        startActivityForResult(new Intent(receiptFolder.this, addReceipt.class), REQUEST_RECIEPT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECIEPT && resultCode == RESULT_OK) {
            Receipt newReceipt = (Receipt)data.getParcelableExtra("newReceipt");
            if(newReceipt != null){
                GlobalFolderList.get(name).addReceipt(newReceipt);
            }
        }
    }
    //Lucas' stuff starts here
    //This sets the folder's name depending on what folder is clicked in the RecyclerView
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents"); //Log.d is just to check if the method is working

        if(getIntent().hasExtra("name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String folderName = getIntent().getStringExtra("name");


        }
    }

    private void setName(String folderName) {
        Log.d(TAG, "setName: setting the name of the folder to widgets");

        TextView name = findViewById(R.id.info_text); //change info_text to something in folder_file.xml MUST DO
        name.setText(folderName);
    }//Lucas' stuff ends here
}
