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
        itemList.add(new Item("Samsung Note 20", "25/10/2024", "Posts and Telecommunications Institute of Technology", "09:34"));
        itemList.add(new Item("Samsung Note 20", "25/10/2024", "Tops Market Hà Đông", "07:24"));
        itemList.add(new Item("Samsung Note 20", "24/10/2024", "20.9808822,105.7502131", "21:17"));
        itemList.add(new Item("Samsung Note 20", "24/10/2024", "20.982485,105.7534751", "20:58"));
        itemList.add(new Item("Samsung Note 20", "24/10/2024", "Bò Tơ Quán Mộc Hà Đông", "20:32"));
        itemList.add(new Item("Samsung Note 20", "24/10/2024", "20.987288,105.7763111", "17:12"));
        itemList.add(new Item("Samsung Note 20", "23/10/2024", "Sân Bóng Trung Văn", "09:14"));
        itemList.add(new Item("Samsung Note 20", "22/10/2024", "20.985388,105.77631145", "16:10"));
        itemList.add(new Item("Samsung Note 20", "22/10/2024", "21.932488,104.7763111", "12:16"));
        itemList.add(new Item("Samsung Note 20", "22/10/2024", "The Light Apartment", "10:03"));
        itemList.add(new Item("Samsung Note 20", "21/10/2024", "VP 91 Khuất Duy Tiến (Big C Mỹ Đình)", "09:34"));
        itemList.add(new Item("Samsung Note 20", "21/10/2024", "Trạm Xăng Dầu Nguyễn Quý Đức ", "07:21"));
        itemList.add(new Item("Samsung Note 20", "20/10/2024", "Me Tri Dormitory", "04:06"));
        itemList.add(new Item("Samsung Note 20", "20/10/2024", "20.985388,105.77631145", "18:00"));
        itemList.add(new Item("Samsung Note 20", "20/10/2024", "20.985388,105.77631145 ", "15:26"));

        // Sử dụng CustomAdapter
        MyAdapterHistory adapter = new MyAdapterHistory(this, itemList);
        listView.setAdapter(adapter);
    }
}