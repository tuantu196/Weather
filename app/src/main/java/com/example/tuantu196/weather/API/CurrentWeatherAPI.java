package com.example.tuantu196.weather.API;

import com.example.tuantu196.weather.model.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tube on 1/13/2018.
 */


public interface CurrentWeatherAPI {

    @GET("weather")
    Call<CurrentWeather> getByLoc(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId
    );
}
