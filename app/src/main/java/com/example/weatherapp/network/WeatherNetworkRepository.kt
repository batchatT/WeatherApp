package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.commonWeather.SingleLiveEvent
import com.example.weatherapp.utils.API_KEY
import com.example.weatherapp.utils.UNIT_METRIC
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherNetworkRepository @Inject constructor(
    private val networkApi: NetworkApiInterface
) : NetworkRepository {

    private val weatherList = arrayListOf<WeatherModel>()

    init{
        Log.d("WeatherRepo", "Init")
    }

    override fun getFromNetwork(
        weatherLiveEvent: SingleLiveEvent<WeatherModel>,
        errorEventFromResponse: MutableLiveData<Boolean>,
        cityName: String
    ) {
        val correctCityName = cityName.trim()
        if (weatherList.isEmpty()) {
            getForecastByCityName(weatherLiveEvent, errorEventFromResponse, correctCityName)

        } else if (weatherList.none { it.weather[0].city.equals(correctCityName, ignoreCase = true) }) {
                getForecastByCityName(weatherLiveEvent,errorEventFromResponse, correctCityName)
        }
    }

    private fun getForecastByCityName(
        weatherLiveEvent: SingleLiveEvent<WeatherModel>,
        errorEventFromResponse: MutableLiveData<Boolean>,
        cityName: String
    ) {
        if (cityName.isNotEmpty()) {
            networkApi.getWeatherData(
                UNIT_METRIC,
                API_KEY,
                cityName
            ).enqueue(
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