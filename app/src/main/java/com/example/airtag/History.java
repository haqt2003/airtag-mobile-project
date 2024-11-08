package com.example.airtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    ImageView devices, addItems, history, profile;
    TextView devicesText, addItemsText, historyText, profileText,signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
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
                Intent intent = new Intent(History.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Add_Items.class);
                startActivity(intent);
            }
        });
        addItemsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Add_Items.class);
                startActivity(intent);
            }
        });
        devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Devices.class);
                startActivity(intent);
            }
        });
        devicesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Devices.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Profile.class);
                startActivity(intent);
            }
        });
        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Profile.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.listView);
        List<Item> itemList = new ArrayList<>();

        // Thêm dữ liệu vào itemList
        itemList.add(new Item("Redmi Note 11 pro", "24/09/2024", "Museum of contemporary Art", "10:00"));
        itemList.add(new Item("Redmi Note 11 pro", "02/01/2024", "Museum of contemporary Art", "11:00"));
        itemList.add(new Item("Redmi Note 11 pro", "03/01/2024", "Museum of contemporary Art The Rock Pitt St Tower ", "12:00"));
        itemList.add(new Item("Redmi Note 11 pro", "24/09/2024", "Museum of contemporary Art", "10:00"));
        itemList.add(new Item("Redmi Note 11 pro", "02/01/2024", "Museum of contemporary Art", "11:00"));
        itemList.add(new Item("Redmi Note 11 pro", "03/01/2024", "Museum of contemporary Art The Rock Pitt St Tower ", "12:00"));
        itemList.add(new Item("Redmi Note 11 pro", "24/09/2024", "Museum of contemporary Art", "10:00"));
        itemList.add(new Item("Redmi Note 11 pro", "02/01/2024", "Museum of contemporary Art", "11:00"));
        itemList.add(new Item("Redmi Note 11 pro", "03/01/2024", "Museum of contemporary Art The Rock Pitt St Tower ", "12:00"));
        itemList.add(new Item("Redmi Note 11 pro", "24/09/2024", "Museum of contemporary Art", "10:00"));
        itemList.add(new Item("Redmi Note 11 pro", "02/01/2024", "Museum of contemporary Art", "11:00"));
        itemList.add(new Item("Redmi Note 11 pro", "03/01/2024", "Museum of contemporary Art The Rock Pitt St Tower ", "12:00"));
        itemList.add(new Item("Redmi Note 11 pro", "24/09/2024", "Museum of contemporary Art", "10:00"));
        itemList.add(new Item("Redmi Note 11 pro", "02/01/2024", "Museum of contemporary Art", "11:00"));
        itemList.add(new Item("Redmi Note 11 pro", "03/01/2024", "Museum of contemporary Art The Rock Pitt St Tower ", "12:00"));

        // Sử dụng CustomAdapter
        MyAdapterHistory adapter = new MyAdapterHistory(this, itemList);
        listView.setAdapter(adapter);
    }
}