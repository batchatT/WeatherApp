package com.example.weatherapp.network

interface RetrofitProvider {

    fun <T> provideService(service : Class<T>): T
}
