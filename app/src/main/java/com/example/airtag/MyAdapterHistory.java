package com.example.airtag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapterHistory extends ArrayAdapter<Item> {
    public MyAdapterHistory(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tái sử dụng View nếu có
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        // Lấy item hiện tại
        Item currentItem = getItem(position);

        // Thiết lập dữ liệu cho các TextView
        TextView textViewDeviceName = convertView.findViewById(R.id.textViewDeviceName);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewLocation = convertView.findViewById(R.id.textViewLocation);
        TextView textViewTime = convertView.findViewById(R.id.textViewTime);

        textViewDeviceName.setText(currentItem.getDeviceName());
        textViewDate.setText(currentItem.getDate());
        textViewLocation.setText(currentItem.getLocation());
        textViewTime.setText(currentItem.getTime());

        return convertView;
    }
}

