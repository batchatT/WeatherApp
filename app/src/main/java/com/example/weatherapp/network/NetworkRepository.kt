package com.example.weatherapp.network

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.commonWeather.SingleLiveEvent

interface NetworkRepository{

    fun getFromNetwork(weatherLiveEvent: SingleLiveEvent<WeatherModel>,
                       errorEventFromResponse: MutableLiveData<Boolean>,
                       cityName: String
    )
}
