package com.example.garbagecollector;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class complaint_status extends AppCompatActivity {
    RecyclerView recyclerViewSD;
    ArrayList<Complaint> userArrayList;
    CustomAdaptorComplaint adaptor;
    DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_complaint_status);

        recyclerViewSD = findViewById(R.id.recyclerViewSD);
        database = FirebaseDatabase.getInstance().getReference().child("Complaint_data");
        userArrayList = new ArrayList<>();

        recyclerViewSD.setHasFixedSize(true);
        recyclerViewSD.setLayoutManager(new LinearLayoutManager(this));

        adaptor = new CustomAdaptorComplaint(this,userArrayList);
        recyclerViewSD.setAdapter(adaptor);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint comp = dataSnapshot.getValue(Complaint.class);
                    userArrayList.add(comp);
                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(complaint_status.this, "An error occured", Toast.LENGTH_SHORT).show();
            }
        });

    }
}