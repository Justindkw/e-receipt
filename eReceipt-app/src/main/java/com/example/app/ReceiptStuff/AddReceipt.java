package com.example.app.ReceiptStuff;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReceipt extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextInputEditText timerDate;
    private String folderName;
    private Bitmap receiptPhoto;
    private ImageButton cameraButton;
    private ImageButton timerButton;
    private TextInputLayout timerLayout;
    private boolean haveTimer;
    //int to compare if it is our request
    static final int REQUEST_IMAGE_CAPTURE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);
        timerDate = findViewById(R.id.timerDate);
        timerButton = findViewById(R.id.addTimer);folderName = getIntent().getStringExtra("folderName");
        cameraButton = findViewById(R.id.toCameraButton);
        timerLayout = findViewById(R.id.timerInputLayout);
        ((Switch)findViewById(R.id.enableTimer)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                haveTimer = isChecked;
                if(isChecked){
                    timerButton.animate().alpha(1.0f).setDuration(200);
                    timerButton.setEnabled(true);
                    timerLayout.animate().alpha(1.0f).setDuration(200);
                    timerDate.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                }
                else{
                    timerButton.animate().alpha(0.0f).setDuration(200);
                    timerButton.setEnabled(false);
                    timerLayout.animate().alpha(0.0f).setDuration(200);
                    timerDate.setInputType(0);
                }
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTakePhoto();
            }
        });
        (findViewById(R.id.doneReceiptButton)).setOnClickListener(new View.OnClickListener() {
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
        timerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {



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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateCalenderButton(int from, int to, int time){
        ValueAnimator colorAnimation = ValueAnimator.ofArgb(from, to);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                timerButton.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.setDuration(time); // milliseconds
        colorAnimation.start();
    }
    //PHOTO STUFF BEINGS HERE

    //asks for photo permission
    private void activeTakePhoto() {
        //checks if self permission is equal to camera activity permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            //if not take further action
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
        } else {
            //else take picture
            takePicture();
        }

    }
    //further action for permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 110) {
            //further action taken to take picture
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }}
    //goes to camera activity for photo
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    //PHOTO STUFF ENDS HERE

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        //returns the resulted receipt back to ReceiptFolder
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            receiptPhoto = (Bitmap)data.getExtras().get("data");
            ((ImageButton)findViewById(R.id.toCameraButton)).setImageBitmap(receiptPhoto);
        }
    }
}
