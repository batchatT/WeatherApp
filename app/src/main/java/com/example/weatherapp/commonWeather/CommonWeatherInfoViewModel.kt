package com.example.weatherapp.commonWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.*
import com.example.weatherapp.network.NetworkRepository
import javax.inject.Inject

class CommonWeatherInfoFragmentViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val searchByNameWeatherLiveData: LiveData<WeatherModel>
        get() = _searchByNameWeatherLiveData
    private var _searchByNameWeatherLiveData = SingleLiveEvent<WeatherModel>()

    val errorEventFromResponse: LiveData<Boolean>
        get() = _errorLiveEventFromResponse
    private var _errorLiveEventFromResponse = MutableLiveData<Boolean>()

    fun errorIsShown() {
        _errorLiveEventFromResponse.value = false
    }

    fun onClickFindWeatherInfo(cityName: String) {
        networkRepository.getFromNetwork(
            _searchByNameWeatherLiveData,
            cityName,
            _errorLiveEventFromResponse
        )
    }
}