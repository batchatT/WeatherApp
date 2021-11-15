package com.example.weatherapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfo(
    val city: String,
    val degree:Int,
    val humidity:Int,
    val pressure:Int,
    val windSpeed:Double
) : Parcelable
