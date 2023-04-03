package com.example.garbagecollector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {


    EditText user , pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_login);

        user = findViewById(R.id.admin);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.admin_login);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().equals("mumbins") && pass.getText().toString().equals("admin")){
                    Toast.makeText(admin_login.this, "Admin Logged in", Toast.LENGTH_SHORT).show();
                    Intent to_admin = new Intent(admin_login.this,admin.class);
                    startActivity(to_admin);
                }
                else{

                    Toast.makeText(admin_login.this, user.getText().toString()+ "" + pass.getText().toString()+ "" +"",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}