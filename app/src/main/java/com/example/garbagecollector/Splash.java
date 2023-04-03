package com.example.garbagecollector;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static final int PROGRESS_DURATION = 4000; // 4 seconds
    private ProgressBar mProgressBar;
    private int mProgressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        mProgressBar = findViewById(R.id.progressBar);


        Thread thread = new Thread(){
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus++;
                    try {

                        Thread.sleep(PROGRESS_DURATION / 150);
                    }
                    catch (Exception e){
                    }
                    mProgressBar.setProgress(mProgressStatus);
                }

                    Intent to_main = new Intent(Splash.this,MainActivity.class);
                    startActivity(to_main);


            }
        };thread.start();
    }
}