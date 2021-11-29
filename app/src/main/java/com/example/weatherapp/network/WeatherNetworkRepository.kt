package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.commonWeather.SingleLiveEvent
import retrofit2.*
import javax.inject.Inject

class WeatherNetworkRepository @Inject constructor(
    private val networkApi: NetworkApiInterface
) : NetworkRepository {

    private val weatherList = arrayListOf<WeatherModel>()

    init{
        Log.d("WeatherRepo", "Init")
    }

    override fun getFromNetwork(
        weatherLiveEvent: SingleLiveEvent<WeatherModel>,
        cityName: String,
        errorEventFromResponse: MutableLiveData<Boolean>
    ) {
        val correctCityName = cityName.trim()
        if (weatherList.isEmpty()) {
            getForecastByCityName(weatherLiveEvent, correctCityName, errorEventFromResponse)
        } else {
            if (weatherList.none { it.weather[0].city.equals(correctCityName, ignoreCase = true) }) {
                getForecastByCityName(weatherLiveEvent, correctCityName, errorEventFromResponse)
            }
        }
    }

    private fun getForecastByCityName(
        weatherLiveEvent: SingleLiveEvent<WeatherModel>,
        cityName: String,
        errorEventFromResponse: MutableLiveData<Boolean>
    ) {
        if (cityName.isNotEmpty()) {
            networkApi.getWeatherData(cityName).enqueue(
                object : Callback<WeatherModel> {
                    override fun onResponse(
                        call: Call<WeatherModel>,
                        response: Response<WeatherModel>
                    ) {
                        if (response.isSuccessful && response.body()?.weather?.isEmpty() == false) {
                            response.body()?.let { weatherLiveEvent.value = it }
                            weatherLiveEvent.value?.let { weatherList.add(it) }
                        } else {
                            errorEventFromResponse.value = true
                        }
                    }

                    override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                        errorEventFromResponse.value = true
                    }
                }
            )
        }
    }
}