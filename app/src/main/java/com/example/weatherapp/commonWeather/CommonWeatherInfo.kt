package com.example.weatherapp.commonWeather

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
import com.example.weatherapp.CustomRepository
import com.example.weatherapp.R
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.WeatherInfo
import com.example.weatherapp.databinding.FragmentCommonWeatherInfoBinding
import java.util.ArrayList


class CommonWeatherInfo : Fragment() {

    private lateinit var commonWeatherViewModel: CommonWeatherInfoViewModel

    private lateinit var adapter: WeatherAdapter

    private lateinit var binding: FragmentCommonWeatherInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        commonWeatherViewModel = CommonWeatherInfoViewModel(CustomRepository())

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_common_weather_info,
            container,
            false
        )

        adapter = WeatherAdapter(commonWeatherViewModel.weatherList)

        setBinding()

        Log.d("CommonWeatherInfo", "Checl")

        adapter.setOnWeatherClickListener(object: WeatherAdapter.OnWeatherListener {
            override fun onWeatherClick(position: Int) {
                val bundle = Bundle()
                bundle.putParcelable("Weather", commonWeatherViewModel.weatherList[position])
                this@CommonWeatherInfo.findNavController()
                    .navigate(R.id.action_commonWeatherInfo_to_detailedWeatherInfo, bundle)
            }

        })

        return binding.root
    }


    fun filter(text: String?) {
        val temp: ArrayList<WeatherInfo> = ArrayList<WeatherInfo>()
        for (weatherItem in commonWeatherViewModel.weatherList) {
            if (text != null) {
                if (weatherItem.city.contains(text, ignoreCase = true)) {
                    temp.add(weatherItem)
                }
            }
        }
        adapter.filter(temp)
    }

    private fun setBinding(){
        binding.weatherRecycler.adapter = adapter

        binding.weatherRecycler.layoutManager = LinearLayoutManager(context)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.findCityEdit.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })
    }


}