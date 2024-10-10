package com.example.airtag;

public class Item {
    private String deviceName;
    private String date;
    private String location;
    private String time;

    public Item(String deviceName, String date, String location, String time) {
        this.deviceName = deviceName;
        this.date = date;
        this.location = location;
        this.time = time;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}

