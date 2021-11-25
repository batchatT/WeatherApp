package com.example.weatherapp

import android.app.Application
import android.widget.Toast
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

class WeatherApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

}