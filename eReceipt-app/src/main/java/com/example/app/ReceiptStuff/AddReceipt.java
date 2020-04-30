package com.example.app.ReceiptStuff;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReceipt extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText timerDate;
    private String folderName;
    private Bitmap receiptPhoto;
    private ImageButton cameraButton;
    private Switch enabler;
    static final int REQUEST_IMAGE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);
        timerDate = findViewById(R.id.timerDate);
        findViewById(R.id.addTimer).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                folderName = getIntent().getStringExtra("folderName");
                cameraButton = findViewById(R.id.toCameraButton);
                cameraButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("button","click click");
                        toCameraScreen();
                    }
                });
                ((Button)findViewById(R.id.doneReceiptButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Folder folder = GlobalFolderList.get(folderName);
                            //adds new receipt to folder
                            folder.addReceipt(new Receipt(receiptPhoto,
                                    Double.parseDouble(((EditText) findViewById(R.id.cost)).getText().toString()),
                                    ((EditText) findViewById(R.id.company)).getText().toString(),
                                    new SimpleDateFormat("dd/MM/yyyy").parse(timerDate.getText().toString()),
                                    true
                            ));
                            //returns with receipt position (it is kinda redundant since the pos is always last)
                            setResult(RESULT_OK, new Intent().putExtra("receiptPos", folder.size() - 1));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                });


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        AddReceipt.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear,mMonth,mDay);
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color
                .TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                    timerDate.setText(day+"/"+month+"/"+year);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        //returns the resulted receipt back to ReceiptFolder
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            receiptPhoto = data.getParcelableExtra("photo");
            cameraButton.setImageBitmap(receiptPhoto);
        }
    }
    private void toCameraScreen(){
        startActivityForResult(new Intent(this, CameraScreen.class),REQUEST_IMAGE);
    }
}
