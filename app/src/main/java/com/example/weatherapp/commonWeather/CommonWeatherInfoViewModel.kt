package com.example.weatherapp.commonWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.*
import com.example.weatherapp.network.NetworkRepositoryInterface

class CommonWeatherInfoFragmentViewModel(var networkRepository: NetworkRepositoryInterface) : ViewModel() {

    val weatherList: LiveData<List<WeatherModel>>
        get() = _weatherList
    private var _weatherList = networkRepository.getFromNetwork(MutableLiveData(), "")

    fun onClickFindWeatherInfo(cityName: String){
        _weatherList = networkRepository.getFromNetwork(MutableLiveData(), cityName)
    }
}