package com.example.weatherapp.network

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherModel

interface NetworkRepositoryInterface{
    fun getFromNetwork(weatherLiveData: MutableLiveData<WeatherModel>, cityName: String): MutableLiveData<List<WeatherModel>>
}