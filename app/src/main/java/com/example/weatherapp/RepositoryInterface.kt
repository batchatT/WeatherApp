package com.example.weatherapp

interface RepositoryInterface{
    fun getFromBD(): List<WeatherInfo>
}