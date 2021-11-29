package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.databinding.FragmentDetailedWeatherInfoBinding
import com.example.weatherapp.utils.WEATHER_INFO_KEY
import java.lang.StringBuilder

class DetailedWeatherInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailedWeatherInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detailed_weather_info,
            container,
            false
        )

        getBundle()

        return binding.root
    }

    private fun getBundle() {
        val bundle = this.arguments
        bundle?.let {
            val weather = bundle.getParcelable<WeatherModel>(WEATHER_INFO_KEY)
            val temp = StringBuilder("")
            weather?.let {
                if (weather.weather.first().mainInfo.degree > 0) {
                    temp.append("+")
                }
                temp.append(weather.weather.first().mainInfo.degree).append("°C")
                binding.cityText.text = weather.weather.first().city
                binding.temperature.text = temp
                binding.humidity.text =
                    getString(R.string.humidity, weather.weather[0].mainInfo.humidity)
                binding.pressure.text =
                    getString(R.string.pressure, weather.weather[0].mainInfo.pressure)
                binding.windSpeed.text =
                    getString(R.string.windspeed, weather.weather.first().windInfo.windSpeed)
            }
        }
    }

}