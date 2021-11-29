package com.example.weatherapp.commonWeather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.network.NetworkRepository
import javax.inject.Inject

class CommonWeatherInfoViewModelFactory @Inject constructor(
    private val networkRepository: NetworkRepository,
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommonWeatherInfoFragmentViewModel::class.java)) {
            return CommonWeatherInfoFragmentViewModel(networkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}