package com.example.tuantu196.weather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tuantu196.weather.API.SevenDayAPI;
import com.example.tuantu196.weather.R;
import com.example.tuantu196.weather.adapter.SevenDayAdapter;
import com.example.tuantu196.weather.model.Item;
import com.example.tuantu196.weather.model.SevenDayForecast;
import com.example.tuantu196.weather.utils.GPSTracker;
import com.example.tuantu196.weather.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tube on 1/14/2018.
 */

public class Screen2Activity extends AppCompatActivity {
    SevenDayAdapter sevenDayAdapter;
    RecyclerView recyclerView;

    String API_Key2 = "9d1d5f43fe2b7a4a75bbc54e376f6b97";
    List<Item> itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.screen2_activity);
        recyclerView = (RecyclerView) findViewById(R.id.rcvListScreen2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        TextView forecast7Day = (TextView)findViewById(R.id.forecast7Day);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sevenDayAdapter);
forecast7Day.setText(getResources().getString(R.string.forecast7Day));
        ImageView backPress = (ImageView) findViewById(R.id.btnBack);

        GPSTracker gpsTracker = new GPSTracker(this);

        gpsTracker.getLocation();

        if (gpsTracker.canGetLocation()) {
            String stringLatitude = String.valueOf(gpsTracker.getLatitude());
            String stringLongitude = String.valueOf(gpsTracker.getLongitude());

            RetrofitClient.getService(SevenDayAPI.class).getByLoc(stringLatitude, stringLongitude,
                    API_Key2).enqueue(new Callback<SevenDayForecast>() {
                @Override
                public void onResponse(Call<SevenDayForecast> call, Response<SevenDayForecast> response) {
                    itemList = new ArrayList<>();
                    for (int i = 0; i < response.body().getList().size(); i++) {
                        itemList.add(new Item(String.valueOf(Math.round(response.body().getList().get(i).getTemp().getMax() - 273)),
                                String.valueOf(Math.round(response.body().getList().get(i).getTemp().getMin() - 273)),
                                String.valueOf(Math.round(response.body().getList().get(i).getHumidity()) + "%"),
                                String.valueOf(response.body().getList().get(i).getSpeed() + "m/s"),
                                String.valueOf(response.body().getList().get(i).getDt()),
                                String.valueOf((response.body().getList().get(i).getWeather().get(0).getDescription()))));
                    }
                    sevenDayAdapter = new SevenDayAdapter(itemList);
                    recyclerView.setAdapter(sevenDayAdapter);

                }

                @Override
                public void onFailure(Call<SevenDayForecast> call, Throwable t) {
                    if (isFinishing()) {
                        return;
                    }
                    Log.d("Fail:", t.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(), call.request().toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        } else {
            gpsTracker.showSettingsAlert();
        }

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
