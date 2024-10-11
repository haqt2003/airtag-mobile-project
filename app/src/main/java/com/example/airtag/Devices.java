package com.example.airtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class Devices extends AppCompatActivity implements OnMapReadyCallback {

    ImageView devices, addItems, history, profile;
    TextView devicesText, addItemsText, historyText, profileText,signOut;
    FrameLayout detailFrame;
    ImageView direction;
    TextView directionText;
    ImageView back;

    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_devices);

        mapView = findViewById(R.id.mapView2);
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
                Intent intent = new Intent(Devices.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, Add_Items.class);
                startActivity(intent);
            }
        });
        addItemsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, Add_Items.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, History.class);
                startActivity(intent);
            }
        });
        historyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, History.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, Profile.class);
                startActivity(intent);
            }
        });
        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices.this, Profile.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.listView);
        List<Device> dataList = new ArrayList<>();

        detailFrame = findViewById(R.id.detailFrame);
        direction = findViewById(R.id.direction);
        directionText = findViewById(R.id.directionText);
        back = findViewById(R.id.back);

        // Thêm dữ liệu vào dataList
        dataList.add(new Device("Redmi Note 11 pro", "Active now", R.drawable.device_icon1));
        dataList.add(new Device("Redmi Watch 2 Lite", "Active 1hr ago", R.drawable.device_icon));

        MyAdapterDevices adapter = new MyAdapterDevices(this, dataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Device selectedItem = (Device) adapter.getItem(position);
//                detailImage.setImageResource(selectedItem.getImageResource());
//                detailText.setText(selectedItem.getText1() + "\n" + selectedItem.getText2()); Lấy thông tin item
                detailFrame.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailFrame.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);
            }
        });

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location = new LatLng(20.980945537565102, 105.78768203432068);
        googleMap.addMarker(new MarkerOptions().position(location).title("PTIT"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }
}