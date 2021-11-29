package com.example.weatherapp.network

import com.example.weatherapp.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiInterface {

    @GET("find")
    fun getWeatherData(
        @Query("units") units: String,
        @Query("appid") appId: String,
        @Query("q") city: String
    ): Call<WeatherModel>
}
