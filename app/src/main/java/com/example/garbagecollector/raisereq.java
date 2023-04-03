package com.example.garbagecollector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class raisereq extends AppCompatActivity {

    TextView a1;
    TextView a2;
    TextView a3;
    TextView a4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_raisereq);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_complaint = new Intent(raisereq.this, complaintform.class);
                to_complaint.putExtra("place", "Balaji colony Opposite Indiabulls Andheri");
                startActivity(to_complaint);
            }
        });


        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_complaint = new Intent(raisereq.this, complaintform.class);
                to_complaint.putExtra("place", "Ramnarian Ruia college L.Nappo Rd Matunga");
                startActivity(to_complaint);
            }
        });


        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_complaint = new Intent(raisereq.this, complaintform.class);
                to_complaint.putExtra("place", "Chintamani CHS Gokhale Road Dadar");
                startActivity(to_complaint);
            }
        });


        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_complaint = new Intent(raisereq.this, complaintform.class);
                to_complaint.putExtra("place", "Indira nagar Opposite Amul factory Mulund");
                startActivity(to_complaint);
            }
        });


    }
}