package com.example.weatherapp.network

import retrofit2.Retrofit

interface RetrofitProvider {
    fun <T> provideRetrofit(service : Class<T>): T
}