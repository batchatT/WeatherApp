package com.example.weatherapp.network

import com.example.weatherapp.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiInterface {
    @GET("find?units=metric&appid=412061e5cfeec723840ae710170e8dfc")
    fun getWeatherData(@Query("q") city: String): Call<WeatherModel>
}