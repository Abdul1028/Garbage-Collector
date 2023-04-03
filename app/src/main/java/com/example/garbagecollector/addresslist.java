package com.example.garbagecollector;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class addresslist extends AppCompatActivity {
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
        setContentView(R.layout.activity_addresslist);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);




        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(addresslist.this);
                altdial.setMessage("Are you sure the bin is full?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    String uid = currentUser.getUid();
                                    String name = currentUser.getDisplayName();
                                    String msg = " Says that the bin at 101, Balaji colony Opposite Indiabulls Andheri is full please use another bin or Use Live location";
                                    Calendar calendar = Calendar.getInstance();
                                    String date_and_time = DateFormat.getDateInstance().format(calendar.getTime());


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference("Bin_data");
                                    Bin bin = new Bin(name,msg,date_and_time);
                                    database.getReference("Bin_data").child(uid).setValue(bin);


                                    Toast.makeText(addresslist.this, "Dear "+currentUser.getDisplayName()+"your request is successfully registered! Bin will be Collected soon", Toast.LENGTH_SHORT).show();

                                } else {
                                    // No user is signed in
                                    Toast.makeText(addresslist.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Confirmation");
                alertDialog.show();
            }
        });


        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(addresslist.this);
                altdial.setMessage("Are you sure the bin is full?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    String uid = currentUser.getUid();
                                    String name = currentUser.getDisplayName();
                                    String msg = " Says that the bin at Ramnarian Ruia college L.Nappo Rd Matunga is full please use another bin or Use Live location";
                                    Calendar calendar = Calendar.getInstance();
                                    String date_and_time = DateFormat.getDateInstance().format(calendar.getTime());


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference("Bin_data");
                                    Bin bin = new Bin(name,msg,date_and_time);
                                    database.getReference("Bin_data").child(uid).setValue(bin);


                                    Toast.makeText(addresslist.this, "currr"+currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                                } else {
                                    // No user is signed in
                                    Toast.makeText(addresslist.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Confirmation");
                alertDialog.show();
            }
        });



        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(addresslist.this);
                altdial.setMessage("Are you sure the bin is full?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    String uid = currentUser.getUid();
                                    String name = currentUser.getDisplayName();
                                    String msg = " Says that the bin at Chintamani CHS Gokhale Road Dadar is full please use another bin or Use Live location";
                                    Calendar calendar = Calendar.getInstance();
                                    String date_and_time = DateFormat.getDateInstance().format(calendar.getTime());


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference("Bin_data");
                                    Bin bin = new Bin(name,msg,date_and_time);
                                    database.getReference("Bin_data").child(uid).setValue(bin);


                                    Toast.makeText(addresslist.this, "currr"+currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                                } else {
                                    // No user is signed in
                                    Toast.makeText(addresslist.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Confirmation");
                alertDialog.show();
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(addresslist.this);
                altdial.setMessage("Are you sure the bin is full?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    String uid = currentUser.getUid();
                                    String name = currentUser.getDisplayName();
                                    String msg = " Says that the bin at Indira nagar Opposite Amul factory Mulund is full please use another bin or Use Live location";
                                    Calendar calendar = Calendar.getInstance();
                                    String date_and_time = DateFormat.getDateInstance().format(calendar.getTime());


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference("Bin_data");
                                    Bin bin = new Bin(name,msg,date_and_time);
                                    database.getReference("Bin_data").child(uid).setValue(bin);


                                    Toast.makeText(addresslist.this, "currr"+currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                                } else {
                                    // No user is signed in
                                    Toast.makeText(addresslist.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Confirmation");
                alertDialog.show();
            }
        });

    }

    public void readName(String uid){
        String n="not theerr";
        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User_data");
        ref.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot ds = task.getResult();
                        String n = String.valueOf(ds.child("name").getValue());
                        System.out.println(n);
                        Toast.makeText(addresslist.this, "name "+n, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void readn(String uid){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("User_data");

        ref.child(uid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                Toast.makeText(addresslist.this, "name is "+name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });

    }

}