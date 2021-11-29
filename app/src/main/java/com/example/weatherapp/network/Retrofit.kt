package com.example.weatherapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

class Retrofit @Inject constructor() : RetrofitProvider{

    override fun <T> provideService(service : Class<T>): T {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(service)
    }
}