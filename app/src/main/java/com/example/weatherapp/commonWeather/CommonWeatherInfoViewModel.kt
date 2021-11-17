package com.example.weatherapp.commonWeather

import androidx.lifecycle.ViewModel
import com.example.weatherapp.RepositoryInterface

class CommonWeatherInfoViewModel(repository: RepositoryInterface) : ViewModel() {

    val weatherList = repository.getFromBD()

}