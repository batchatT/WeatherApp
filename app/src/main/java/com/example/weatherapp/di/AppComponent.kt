package com.example.weatherapp.di

import com.example.weatherapp.commonWeather.CommonWeatherInfoFragment
import dagger.Component

@Component(modules = [NetworkModule::class, RetrofitModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(commonWeatherInfoFragment: CommonWeatherInfoFragment)
}