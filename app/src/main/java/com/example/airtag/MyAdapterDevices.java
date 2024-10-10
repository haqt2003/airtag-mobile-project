package com.example.airtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapterDevices extends BaseAdapter {

    private Context context;
    private List<Device> dataList; // Thay 'YourDataModel' bằng class dữ liệu của bạn

    public MyAdapterDevices(Context context, List<Device> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_devices, parent, false);
        }

        TextView textView1 = convertView.findViewById(R.id.textView1);
        TextView textView2 = convertView.findViewById(R.id.textView2);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        Device data = dataList.get(position);
        textView1.setText(data.getText1()); // Thay 'getText1()' bằng phương thức của bạn
        textView2.setText(data.getText2()); // Thay 'getText2()' bằng phương thức của bạn
        imageView.setImageResource(data.getImageResource()); // Thay 'getImageResource()' bằng phương thức của bạn

        return convertView;
    }
}
