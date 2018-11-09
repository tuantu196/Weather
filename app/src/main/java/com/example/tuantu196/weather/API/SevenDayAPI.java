package com.example.tuantu196.weather.API;



import com.example.tuantu196.weather.model.SevenDayForecast;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tube on 1/14/2018.
 */

public interface SevenDayAPI {
        @GET("forecast/daily")
        Call<SevenDayForecast> getByLoc(
                @Query("lat") String lat,
                @Query("lon") String lon,
                @Query("appid") String appId
        );
    }

