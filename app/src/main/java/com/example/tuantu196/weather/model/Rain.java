package com.example.tuantu196.weather.model;

/**
 * Created by KienVT9 on 2017/12/28.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rain {

    @SerializedName("3h")
    @Expose
    private Double _3h;

    public Double get3h() {
        return _3h;
    }

    public void set3h(Double _3h) {
        this._3h = _3h;
    }

}