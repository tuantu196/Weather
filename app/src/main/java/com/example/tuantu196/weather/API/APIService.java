package com.example.tuantu196.weather.API;

import com.example.tuantu196.weather.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tube on 1/13/2018.
 */

public interface APIService {


    @GET("forecast")
    Call<Example> findByName(
            @Query("q") String name,
            @Query("appid") String appId
    );
}