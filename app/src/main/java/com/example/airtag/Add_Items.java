package com.example.airtag;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class Add_Items extends AppCompatActivity  implements OnMapReadyCallback {
    ImageView devices, addItems, history, profile;
    TextView devicesText, addItemsText, historyText, profileText,signOut;

    private MapView mapView;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng destination = new LatLng(20.2427327, 106.0024063);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        devices = findViewById(R.id.devices);
        addItems = findViewById(R.id.addItems);
        history = findViewById(R.id.history);
        profile = findViewById(R.id.profile);
        signOut = findViewById(R.id.signOut);
        devicesText = findViewById(R.id.devicesText);
        addItemsText = findViewById(R.id.addItemsText);
        historyText = findViewById(R.id.historyText);
        profileText = findViewById(R.id.profileText);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, Devices.class);
                startActivity(intent);
            }
        });
        devicesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, Devices.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, History.class);
                startActivity(intent);
            }
        });
        historyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, History.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, Profile.class);
                startActivity(intent);
            }
        });
        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Items.this, Profile.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getCurrentLocationAndDrawRoute() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            drawRoute(currentLocation, destination);
                            moveCameraToLocation(currentLocation);
                            addCurrentLocationMarker(currentLocation);
                        }
                    }
                });
    }




    private void drawRoute(LatLng start, LatLng end) {
        if (googleMap != null) {
            PolylineOptions polylineOptions = new PolylineOptions().add(start, end).width(5).color(ContextCompat.getColor(Add_Items.this, R.color.colorPrimary));
            googleMap.addPolyline(polylineOptions);
            googleMap.addMarker(new MarkerOptions().position(end).title("Destination"));
        }
    }
    // private void showCurrentLocation(LatLng currentLocation) {
    //     if (googleMap != null) {
    //         googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
    //         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
    //     }
    // }
    private void moveCameraToLocation(LatLng location) {
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }
    private void addCurrentLocationMarker(LatLng location) {
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title("Current Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
    }
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        // Enable the location layer if permissions are granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            getCurrentLocationAndMoveCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
    private void getCurrentLocationAndMoveCamera() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            moveCameraToLocation(currentLocation);
                            addCurrentLocationMarker(currentLocation);
                        } else {
                            // If location is null, move camera to a default location
                            moveCameraToLocation(destination);
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                    getCurrentLocationAndMoveCamera();
                }
            }
        }
    }
}