package com.example.weatherapp.network

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

class WeatherNetworkRepository : NetworkRepositoryInterface {

    private val weatherList = arrayListOf<WeatherModel>()
    private val weatherListLiveData = MutableLiveData<List<WeatherModel>>()

    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    private val weatherNetworkApi: NetworkApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        weatherNetworkApi = retrofit.create(NetworkApiInterface::class.java)
    }

    override fun getFromNetwork(
        weatherLiveData: MutableLiveData<WeatherModel>,
        cityName: String
    ): MutableLiveData<List<WeatherModel>> {
        if (weatherList.isEmpty()) {
                creatingRequest(weatherLiveData, cityName)
        }
        else{
            for(city in weatherList)
                if(city.weather[0].city != cityName) {
                        creatingRequest(weatherLiveData, cityName)
                }
        }
        return weatherListLiveData
    }

    private fun creatingRequest(
        weatherLiveData: MutableLiveData<WeatherModel>,
        cityName: String
    ) {
        if (cityName.isNotEmpty() && !cityName.all { it in " " }) {
            weatherNetworkApi.getWeatherData(cityName).enqueue(
                object : Callback<WeatherModel> {
                    override fun onResponse(
                        call: Call<WeatherModel>,
                        response: Response<WeatherModel>
                    ) {
                        if (response.isSuccessful && response.body()?.weather?.isEmpty() == false) {
                            response.body()?.let { weatherLiveData.value = it }
                            weatherLiveData.value?.let { weatherList.add(it) }
                            weatherListLiveData.value = weatherList
                        }
                    }

                    override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                        Log.d("TestRepo", "Failure: $t")
                    }
                }
            )
        }
    }
}