package com.example.airtag;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
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

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.json.JSONObject;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Add_Items extends AppCompatActivity  implements OnMapReadyCallback {
    ImageView devices, addItems, history, profile;
    TextView devicesText, addItemsText, historyText, profileText,signOut;

    // private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_WRITE_PERMISSION = 113;
    private MapView mapView;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng destination = new LatLng(20.2427327, 106.0024063);

    private EditText editTextText;
    private Uri pendingUri;
    
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


        
        editTextText = findViewById(R.id.editTextText);

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
        View btnAddItem = findViewById(R.id.button);
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
         btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextText.getText().toString();
                String message = "Add devices: " + inputText + " - Encode: gmByY4e2vT2jj8vDz61NsPRydhLKUQYxZ/iUlA==";
                Toast.makeText(Add_Items.this, message, Toast.LENGTH_SHORT).show();

                // Insert file into MediaStore and request write permission
//                insertFileIntoMediaStore(inputText);
            }
        });
    }

        

        private void insertFileIntoMediaStore(String inputText) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, inputText + ".json");
                values.put(MediaStore.MediaColumns.MIME_TYPE, "application/json");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/");

                Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
                if (uri != null) {
                    pendingUri = uri;
                    requestWritePermission(uri);
                } else {
                    Toast.makeText(this, "Error inserting file into MediaStore", Toast.LENGTH_LONG).show();
                }
            }
             private void requestWritePermission(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            List<Uri> uris = new ArrayList<>();
            uris.add(uri);

            PendingIntent pendingIntent = MediaStore.createWriteRequest(getContentResolver(), uris);
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), REQUEST_WRITE_PERMISSION, null, 0, 0, 0, null);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error requesting write permission", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "This feature requires Android 11 or higher", Toast.LENGTH_LONG).show();
        }
    }
    private void saveJsonToFile(String inputText, Uri uri) {
        try {
            // Create JSON object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", inputText);
            jsonObject.put("encodeBase64", Base64.encodeToString(inputText.getBytes(), Base64.DEFAULT));

            // Save to DCIM folder
            try (OutputStream outputStream = getContentResolver().openOutputStream(uri)) {
                if (outputStream != null) {
                    outputStream.write(jsonObject.toString().getBytes());
                    Toast.makeText(this, "File saved: " + uri.getPath(), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_LONG).show();
        }
    }
    
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (resultCode == Activity.RESULT_OK && pendingUri != null) {
                String inputText = editTextText.getText().toString();
                saveJsonToFile(inputText, pendingUri);
            } else {
                Toast.makeText(this, "Write permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    
}