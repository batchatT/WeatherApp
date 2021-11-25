package com.example.weatherapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.commonWeather.CommonWeatherInfoViewModelFactory
import dagger.Module

@Module
abstract class ViewModelModule {

    abstract fun provideViewModelFactory(factory: CommonWeatherInfoViewModelFactory): ViewModelProvider.Factory

}