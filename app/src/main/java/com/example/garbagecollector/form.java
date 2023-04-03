package com.example.garbagecollector;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class form extends AppCompatActivity {

    EditText name ,contact , msg;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_form);

        name = findViewById(R.id.c_name);
        contact = findViewById(R.id.c_contact);
        msg = findViewById(R.id.c_msg);

        btn = findViewById(R.id.c_btn);


        String namee = name.getText().toString();
        String contactt = contact.getText().toString();
        String msgg = contact.getText().toString();
        String place = getIntent().getStringExtra("place");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(form.this, namee+contactt+msgg+place, Toast.LENGTH_SHORT).show();
            }
        });





    }
}