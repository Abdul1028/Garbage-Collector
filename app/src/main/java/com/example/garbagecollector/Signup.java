package com.example.garbagecollector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Signup extends AppCompatActivity {

    EditText name , email , password, confirm  ;

    String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    Button create;

    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.namebox);
        email = findViewById(R.id.emailbox);
        password = findViewById(R.id.passwordbox);
        confirm = findViewById(R.id.confirmbox);
        create = findViewById(R.id.createbtn);
        progressDialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              signIn();
            }
        });



    }

    private void signIn() {
        String n = name.getText().toString();
        String e = email.getText().toString();
        String p = password.getText().toString();
        String c = confirm.getText().toString();

        Calendar calendar = Calendar.getInstance();
        String date_and_time = DateFormat.getDateInstance().format(calendar.getTime());

        if(p.isEmpty() || p.length() < 6 ){
            password.requestFocus();
            password.setError("Password should be of atleast 6 digits");
        }

        else if(!e.matches(emailPattern)){
            email.requestFocus();
            email.setError("Please enter a valid email");
        }

        else if(!p.equals(c)){
            password.requestFocus();
            confirm.setError("Password not match both fields");
        }
        else {

            progressDialog.setMessage("Please wait Account creation in Progress");
            progressDialog.setTitle("Creating Account");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        progressDialog.dismiss();

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference("User_data");
                        Credentials cred = new Credentials (e,n,p,date_and_time,"Not choose to Live Location");
                        String id = task.getResult().getUser().getUid();
                        database.getReference("User_data").child(id).setValue(cred);



                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user != null) {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name.getText().toString().trim())
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Signup.this, "Name also updated", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        sendBackToLogin();
                        Toast.makeText(Signup.this, "Account created successfully now you can Login!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }

    private void sendBackToLogin() {
        Intent to_homepage = new Intent(Signup.this,MainActivity.class);
        to_homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(to_homepage);
    }
}