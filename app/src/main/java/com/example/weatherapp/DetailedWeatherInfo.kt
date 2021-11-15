package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.databinding.FragmentDetailedWeatherInfoBinding
import java.lang.StringBuilder

class DetailedWeatherInfo : Fragment() {

    private lateinit var binding: FragmentDetailedWeatherInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detailed_weather_info, container, false)

        getBundle()

        return binding.root
    }

    private fun getBundle(){
        val bundle = this.arguments
        if (bundle != null) {
            val weather = bundle.getParcelable<WeatherInfo>("Weather")
            val temp = StringBuilder("")
            if (weather != null) {
                if(weather.degree > 0){
                    temp.append("+")
                }
                temp.append(weather.degree).append("°C")
                binding.cityText.text = weather.city
                binding.temperature.text = temp
                binding.humidity.text = getString(R.string.humidity, weather.humidity)
                binding.pressure.text = getString(R.string.pressure, weather.pressure)
                binding.windSpeed.text = getString(R.string.windspeed, weather.windSpeed)
            }
        }
    }

}