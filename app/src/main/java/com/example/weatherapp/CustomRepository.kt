package com.example.weatherapp

class CustomRepository : RepositoryInterface {

    private val weatherList = arrayListOf<WeatherInfo>(
        WeatherInfo("Kyiv", 10, 10, 10, 13.3),
        WeatherInfo("Kharkiv", -1, 10, 10, 3.5),
        WeatherInfo("Dubai", 3, 10, 10, 5.7),
        WeatherInfo("New York", 0, 10, 10, 20.0),
        WeatherInfo("Copenhagen", -9, 10, 10, 23.1)
    )

    override fun getFromBD(): List<WeatherInfo> = weatherList
}