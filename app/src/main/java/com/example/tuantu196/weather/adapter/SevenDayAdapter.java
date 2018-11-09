package com.example.tuantu196.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tuantu196.weather.model.Item;
import com.example.tuantu196.weather.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tube on 1/14/2018.
 */

public class SevenDayAdapter extends RecyclerView.Adapter<SevenDayAdapter.RecyclerViewHolder> {
    List<Item> itemList = new ArrayList<>();

    public SevenDayAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_day, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtDesDay.setText(upperCase(itemList.get(position).getDes()));
        holder.txtHumidity.setText(itemList.get(position).getHumidity());
        holder.txtTemp1Max.setText(itemList.get(position).getMaxTemp());
        holder.txtTemp1Min.setText(itemList.get(position).getMinTemp());
        holder.txtWindSpeed.setText(itemList.get(position).getSpeedWind());
        holder.txtDateNumber.setText(String.valueOf(utcToLocal(itemList.get(position).getDate())));
        holder.txtDayName.setText(String.valueOf(unixDayName(itemList.get(position).getDate())));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtDayName, txtDateNumber, txtDesDay, txtTemp1Max, txtTemp1Min, txtHumidity, txtWindSpeed;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtDayName = (TextView) itemView.findViewById(R.id.txtDayName);
            txtDateNumber = (TextView) itemView.findViewById(R.id.txtDateNumber);
            txtDesDay = (TextView) itemView.findViewById(R.id.txtDesDay);
            txtTemp1Max = (TextView) itemView.findViewById(R.id.txtTemp1Max);
            txtTemp1Min = (TextView) itemView.findViewById(R.id.txtTemp1Min);
            txtHumidity = (TextView) itemView.findViewById(R.id.txtHumidity);
            txtWindSpeed = (TextView) itemView.findViewById(R.id.txtWindSpeed);

        }
    }
    public String upperCase(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        return stringBuilder.toString();

    }
    public String utcToLocal(String utcTime){
        long unix = Long.valueOf(utcTime);
        Date date = new Date((long)unix*1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MM");
        String date1 = dateFormat.format(date);
        return date1;
    }
    public String unixDayName(String utcTime){
        long unix = Long.valueOf(utcTime);
        Date date = new Date((long)unix*1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEEEEEE");
        String date1 = dateFormat.format(date);
        return date1;
    }
}
