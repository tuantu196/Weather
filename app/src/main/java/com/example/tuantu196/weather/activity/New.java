package com.example.tuantu196.weather.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tuantu196.weather.API.CurrentWeatherAPI;
import com.example.tuantu196.weather.API.SevenDayAPI;
import com.example.tuantu196.weather.R;
import com.example.tuantu196.weather.databinding.WeatherActivityBinding;
import com.example.tuantu196.weather.model.CurrentWeather;
import com.example.tuantu196.weather.model.SevenDayForecast;
import com.example.tuantu196.weather.utils.GPSTracker;
import com.example.tuantu196.weather.utils.RetrofitClient;
import com.example.tuantu196.weather.viewmodels.Screen1ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tube on 1/14/2018.
 */

public class New extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable refresh;
    String API_Key = "f937391e773178b5f94bc420b8242982";
    String API_Key2 = "9d1d5f43fe2b7a4a75bbc54e376f6b97";

    Screen1ViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WeatherActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.weather_activity);

        final LinearLayout layout_press = (LinearLayout) findViewById(R.id.layout_press);

        GPSTracker gpsTracker = new GPSTracker(this);
        gpsTracker.getLocation();
        if(gpsTracker.canGetLocation()){
            String lat = String.valueOf(gpsTracker.getLatitude());
            String lon = String.valueOf(gpsTracker.getLongitude());
            viewModel = new Screen1ViewModel(lat,lon);
            binding.setViewModel(viewModel);
            viewModel.getCurrentData();
        }else {
            gpsTracker.showSettingsAlert();
        }


        layout_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(New.this, Screen2Activity.class);
                startActivity(intent);
            }
        });


    }
}



