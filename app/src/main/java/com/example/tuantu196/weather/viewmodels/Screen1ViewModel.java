package com.example.tuantu196.weather.viewmodels;

import android.databinding.BaseObservable;
import android.util.Log;

import com.example.tuantu196.weather.API.CurrentWeatherAPI;
import com.example.tuantu196.weather.API.SevenDayAPI;
import com.example.tuantu196.weather.model.CurrentWeather;
import com.example.tuantu196.weather.model.SevenDayForecast;
import com.example.tuantu196.weather.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by KienVT9 on 2018/01/18.
 */

public class Screen1ViewModel extends BaseObservable {
    String API_Key = "f937391e773178b5f94bc420b8242982";
    String API_Key2 = "9d1d5f43fe2b7a4a75bbc54e376f6b97";

    CurrentWeather currentWeather = new CurrentWeather();
    SevenDayForecast sevenDayForecast = new SevenDayForecast();
    String lat;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    String lon;

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public SevenDayForecast getSevenDayForecast() {
        return sevenDayForecast;
    }

    public void setSevenDayForecast(SevenDayForecast sevenDayForecast) {
        this.sevenDayForecast = sevenDayForecast;
    }

    public Screen1ViewModel(String lat, String lon) {

        this.lat = lat;
        this.lon = lon;
        getCurrentData();
        getSevenDayData();
    }

    public void getCurrentData() {

        RetrofitClient.getService(CurrentWeatherAPI.class).getByLoc(lat, lon, API_Key).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                setCurrentWeather(response.body());
                notifyChange();
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("Fail:", t.getMessage());

            }
        });

    }

    public void getSevenDayData() {
        RetrofitClient.getService(SevenDayAPI.class).getByLoc(lat, lon, API_Key2).enqueue(new Callback<SevenDayForecast>() {
            @Override
            public void onResponse(Call<SevenDayForecast> call, Response<SevenDayForecast> response) {
//                SevenDayForecast sevenDayForecast = response.body();
                if (response.body() == null) {
                    return;
                }
                setSevenDayForecast(response.body());
                notifyChange();
            }

            @Override
            public void onFailure(Call<SevenDayForecast> call, Throwable t) {
                return;
            }
        });
    }

    public String getTemp() {
        if (currentWeather == null || currentWeather.getMain().getTemp() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(currentWeather.getMain().getTemp() - 273));
        }
    }

    public String getName() {
        if (currentWeather == null || currentWeather.getName() == null) {
            return "";
        } else {
            return upperCase(currentWeather.getName());
        }
    }

    public String getDescription() {
        if (currentWeather == null || currentWeather.getWeather() == null || currentWeather.getWeather().size() < 1) {
            return "";
        } else {
            return upperCase(currentWeather.getWeather().get(0).getDescription());
        }
    }

    public String getWind() {
        if (currentWeather == null || currentWeather.getWind() == null) {
            return "";
        } else {
            return String.valueOf(currentWeather.getWind());
        }
    }

    public String getHumidity() {

        if (currentWeather == null || currentWeather.getMain() == null) {
            return "";
        } else {
            return String.valueOf(currentWeather.getMain().getHumidity());
        }
    }

    public String getClouds() {

        if (currentWeather == null || currentWeather.getMain() == null) {
            return "";
        } else {
            return String.valueOf(currentWeather.getClouds());
        }
    }

    public String getPressure() {
        if (currentWeather == null || currentWeather.getMain() == null) {
            return "";
        } else {
            return String.valueOf(currentWeather.getMain().getPressure());
        }
    }

    public String getDesToday() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(0).getWeather().size() < 1 || sevenDayForecast.getList().get(0).getWeather().get(0).getDescription() == null) {
            return "";
        } else {
            return upperCase(String.valueOf(sevenDayForecast.getList().get(0).getWeather().get(0).getDescription()));
        }
    }

    public String getDesNextDay() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(1).getWeather().size() < 1 || sevenDayForecast.getList().get(1).getWeather().get(0).getDescription() == null) {
            return "";
        } else {
            return upperCase(String.valueOf(sevenDayForecast.getList().get(1).getWeather().get(0).getDescription()));
        }
    }

    public String getDesSecondDay() {
        if (sevenDayForecast == null|| sevenDayForecast.getList().get(2).getWeather().get(0).getDescription() == null) {
            return "";
        } else {
            return upperCase(String.valueOf(sevenDayForecast.getList().get(2).getWeather().get(0).getDescription()));
        }
    }

    public String getMaxTempDay1() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(0).getTemp().getMax() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(0).getTemp().getMax()));
        }
    }
    public String getMinTempDay1() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(0).getTemp().getMin() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(0).getTemp().getMin()));
        }
    }
    public String getMaxTempDay2() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(0).getTemp().getMin() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(0).getTemp().getMin()));
        }
    }
    public String getMinTempDay2() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(1).getTemp().getMin() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(1).getTemp().getMin()));
        }
    }
    public String getMaxTempDay3() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(2).getTemp().getMin() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(2).getTemp().getMin()));
        }
    }
    public String getMinTempDay3() {
        if (sevenDayForecast == null || sevenDayForecast.getList().get(2).getTemp().getMin() == null) {
            return "";
        } else {
            return String.valueOf(Math.round(sevenDayForecast.getList().get(2).getTemp().getMin()));
        }
    }


    //    public int getIcon() {
//        if (currentWeather == null) {
//            return R.color.white;
//        } else {
//            switch(currentWeather.getWeather().get(0).getIcon()) {
//                case "01d":
//                    return R.mipmap.day_sunny;
//                break;
//                case "02d":
//                    return R.mipmap.nhieumay;
//                break;
//                case "03d":
//                    return R.mipmap.clouds;
//                break;
//                case "04d":
//                    return R.mipmap.goingtorain;
//                break;
//                case "04n":
//                    return R.mipmap.may_den_6;
//                break;
//                case "10d":
//                    return R.mipmap.muasang;
//                break;
//                case "11d":
//                    return R.mipmap.bao;
//                break;
//                case "13d":
//                    return R.mipmap.tuyet;
//                break;
//                case "01n":
//                    return R.mipmap.troiquang;
//                break;
//                case "02n":
//                    return R.mipmap.img_clear_night;
//                break;
//                case "03n":
//                    return R.mipmap.nhieumaytoi;
//                break;
//                case "10n":
//                    return R.mipmap.muatoi;
//                break;
//                case "11n":
//                    return R.mipmap.bao;
//                break;
//                case "13n":
//                    return R.mipmap.tuyetdem;
//                break;
//                case "50d":
//                    return R.mipmap.suong_mu;
//                break;
//                case "09d":
//                    return R.mipmap.muaphun;
//                break;
//            }
//        }
//r
//    }
    public String upperCase(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        return stringBuilder.toString();

    }

}
