package com.example.weatherapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    @SerializedName("list")
    val weather: List<WeatherInfo>
) : Parcelable

@Parcelize
data class WeatherInfo(
    @SerializedName("name")
    val city: String,

    @SerializedName("main")
    val mainInfo: MainInfo,

    @SerializedName("wind")
    val windInfo: WindInfo
) : Parcelable

@Parcelize
data class MainInfo(
    @SerializedName("temp")
    val degree: Double,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int
) : Parcelable

@Parcelize
data class WindInfo(
    @SerializedName("speed")
    val windSpeed: Double
) : Parcelable


