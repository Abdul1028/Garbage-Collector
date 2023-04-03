package com.example.garbagecollector;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText user , pass;

    ProgressDialog progressDialog;

    TextView loginlink , adminlink;
    Button loginbtn;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        user = findViewById(R.id.Username);
        pass = findViewById(R.id.Password);
        loginlink = findViewById(R.id.loginlink);
        loginbtn=findViewById(R.id.loginbtn);
        adminlink = findViewById(R.id.admin_link);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean("loggedIn", false);

        if (loggedIn) {
            Intent intent = new Intent(MainActivity.this, homepage.class);
            startActivity(intent);
            finish();
        }



        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_signup =new Intent(MainActivity.this,Signup.class);
                startActivity(to_signup);
                Toast.makeText(MainActivity.this, "Create an account from here!", Toast.LENGTH_SHORT).show();
            }
        });


        adminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent to_admin =new Intent(MainActivity.this,admin_login.class);
                startActivity(to_admin);
                Toast.makeText(MainActivity.this, "Admin Login here!", Toast.LENGTH_SHORT).show();
            }
        });




        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logIn();

            }
        });




    }

    private void logIn() {
        String username = user.getText().toString();
        String password = pass.getText().toString();



        if (username.isEmpty()){
            user.setError("Username cannot be empty!");
            user.requestFocus();
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        }

        else if(password.isEmpty()){
            pass.setError("Password cannot be empty!");
            pass.requestFocus();

        }

        else {
            progressDialog.setTitle("Logging IN");
            progressDialog.setMessage("Logging you in please wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();




                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("loggedIn", true);
                        editor.apply();

                        sendToHomePage();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendToHomePage() {
        Intent to_homepage = new Intent(MainActivity.this,homepage.class);
        to_homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(to_homepage);
    }


}