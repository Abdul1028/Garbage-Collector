package com.example.garbagecollector;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class navigation extends AppCompatActivity {
    //Initialize variable
    SupportMapFragment smf;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_navigation);


        //Assign variable
        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    private PermissionRequest permissionRequest;
                    private PermissionToken permissionToken;

                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(navigation.this, "Laude location tera baap on karega?", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }



    //Gets your current Location

    public void getmylocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                            try {


                                if(location != null) {

                                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(generateAddress(location.getLatitude(),location.getLongitude()));

                                    googleMap.addMarker(markerOptions);
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

                                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        @Override
                                        // onMarkerClick Method shows the alert Builder to confirm the Location
                                        public boolean onMarkerClick(Marker marker) {
                                            AlertDialog.Builder altdial = new AlertDialog.Builder(navigation.this);
                                            altdial.setMessage(generateAddress(location.getLatitude(),location.getLongitude())+"\nConfirm Your Location?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //Database Insert Code below

                                                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                                            if (currentUser != null) {
                                                                String uid = currentUser.getUid();
                                                                String mine = "L9EfAojozvPLOs9L4YGJrr5y0Zw1";

                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_data").child(uid).child("location");
                                                                reference.setValue(generateAddress(location.getLatitude(),location.getLongitude()));

                                                                DatabaseReference childRef = FirebaseDatabase.getInstance().getReference(mine);
                                                                System.out.println("User id"+uid);
                                                                DatabaseReference elementRef = childRef.child("location");

                                                                Toast.makeText(navigation.this, "Your location is updated We are shortly coming to collect the garbage!", Toast.LENGTH_SHORT).show();


                                                            } else {
                                                                // No user is signed in
                                                                Toast.makeText(navigation.this, "Sign IN first", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(navigation.this, "You clicked", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                    });

                                }

                                else{
                                    Toast.makeText(navigation.this, "Loction is null", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(navigation.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }



                    }
                });
            }
        });
    }



    //Method to generate the fully qualified Address from Latitude and Longitude
    private String generateAddress(Double lati , Double longi ) {
        String add = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(lati, longi, 1);

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                StringBuilder addressString = new StringBuilder("");
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressString.append(address.getAddressLine(i)).append("\n");
                }
                add = addressString.toString();
            }



        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return add;
    }







}