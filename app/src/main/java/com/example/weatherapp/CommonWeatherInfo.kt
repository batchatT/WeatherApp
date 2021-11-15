package com.example.weatherapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentCommonWeatherInfoBinding
import java.util.ArrayList


class CommonWeatherInfo : Fragment() {

    private lateinit var commonWeatherViewModel: CommonWeatherInfoViewModel

    private lateinit var adapter : WeatherAdapter

    private lateinit var weatherList: ArrayList<WeatherInfo>

    private lateinit var binding: FragmentCommonWeatherInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commonWeatherViewModel = ViewModelProvider(this).get(CommonWeatherInfoViewModel::class.java)
        addWeather()
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_common_weather_info, container, false
        )
        adapter = WeatherAdapter(weatherList)

        binding.weatherRecycler.adapter = adapter
        binding.weatherRecycler.layoutManager = LinearLayoutManager(context)

        binding.lifecycleOwner = viewLifecycleOwner

        Log.d("CommonWeatherInfo", "Checl")

        adapter.setOnWeatherLIstener(object : WeatherAdapter.OnWeatherListener {
            override fun onWeatherClick(position: Int) {
                val bundle = Bundle()
                bundle.putParcelable("Weather", weatherList[position])
                this@CommonWeatherInfo.findNavController()
                    .navigate(R.id.action_commonWeatherInfo_to_detailedWeatherInfo, bundle)
            }

        })

        binding.findCityEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })

        return binding.root
    }


    fun filter(text: String?) {
        val temp: ArrayList<WeatherInfo> = ArrayList<WeatherInfo>()
        for (weather_item in weatherList) {
            if (text != null) {
                if (weather_item.city.contains(text, ignoreCase = true)) {
                    temp.add(weather_item)
                }
            }
        }
        adapter.filter(temp)
    }

    private fun addWeather() {
        weatherList = ArrayList()
        weatherList.add(WeatherInfo("Kyiv", 10, 10, 10, 13.3))
        weatherList.add(WeatherInfo("Kharkiv", -1, 10, 10, 3.5))
        weatherList.add(WeatherInfo("Dubai", 3, 10, 10, 5.7))
        weatherList.add(WeatherInfo("New York", 0, 10, 10, 20.0))
        weatherList.add(WeatherInfo("Copenhagen", -9, 10, 10, 23.1))
    }


}