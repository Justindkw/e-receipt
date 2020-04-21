package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {
    private ProgressBar pbar;
    private int a = 0;
    private TextView textView;
    private Handler handler = new Handler();
    // above - Nicolas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*myButton = findViewById(R.id.cameraButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User taps button
            }
        }); */
//Nicolas's stuff starts here
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        pbar = findViewById(R.id.p_Bar);
        Button button = findViewById(R.id.show_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = pbar.getProgress();
                new Thread(new Runnable() {
                    public void run() {
                        while (a < 100) {
                            a += 1;
                            handler.post(new Runnable() {
                                public void run() {
                                    pbar.setProgress(a);
                                    textView.setText(a + "/" + pbar.getMax());
                                    if (a == 100)
                                        textView.setText(" Your Progess has been Completed");
                                }
                            });
                            try {
                                // Sleep for 50 ms to show progress you can change it as well.
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
//Nicolas's stuff ends here


}







