package com.example.garbagecollector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {

    Button logout , showlocation , livelocation , raise , status;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homepage);

        logout = findViewById(R.id.logout);
        showlocation = findViewById(R.id.show_location);
        livelocation = findViewById(R.id.livelocation);
        raise = findViewById(R.id.raise);
        status = findViewById(R.id.status);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_status = new Intent(homepage.this,complaint_status.class);
                startActivity(to_status);
            }
        });

        raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_raisereq = new Intent(homepage.this,raisereq.class);
                startActivity(to_raisereq);
            }
        });




        showlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_addresslist = new Intent(homepage.this,addresslist.class);
                startActivity(to_addresslist);
            }
        });




        livelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_navigation = new Intent(homepage.this,navigation.class);
                startActivity(to_navigation);

            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("loggedIn");
                editor.apply();


                Intent intent = new Intent(homepage.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(homepage.this, "Logged Out successfully!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });



    }
}