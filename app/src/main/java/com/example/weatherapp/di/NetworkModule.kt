package com.example.weatherapp.di

import com.example.weatherapp.network.NetworkRepository
import com.example.weatherapp.network.WeatherNetworkRepository
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideNetwork(networkRepository: WeatherNetworkRepository): NetworkRepository
}
