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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class complaintform extends AppCompatActivity {

    EditText n , c , cc;
    Button cc_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_complaintform);


        cc_btn = findViewById(R.id.complaint_btn);


        String place = getIntent().getStringExtra("place");




        cc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.name);
                String name = editText.getText().toString();


                EditText editText2 = findViewById(R.id.contact);
                String contact = editText2.getText().toString();

                EditText editText3 = findViewById(R.id.complaint);
                String complaint = editText3.getText().toString();
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (currentUser != null) {
                    String uid = currentUser.getUid();
                    database.getReference("Complaint_data");
                    Complaint comp = new Complaint(name,contact,complaint,place);
                    database.getReference("Complaint_data").child(uid).setValue(comp);
                    Toast.makeText(complaintform.this, name+" "+"your complaint raised successfully!", Toast.LENGTH_SHORT).show();
                }

                else{

                }
            }
        });

    }
}