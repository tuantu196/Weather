package com.example.tuantu196.weather.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Tube on 1/14/2018.
 */

public class Item {
    String maxTemp;
    String minTemp;
    String humidity;
    String speedWind;
    String date;
    String des;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String formattedDate = df.format(c.getTime());

    public Item(String maxTemp, String minTemp, String humidity, String speedWind,String date, String des) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.speedWind = speedWind;
        this.des = des;
        this.date = date;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(String speedWind) {
        this.speedWind = speedWind;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = formattedDate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
