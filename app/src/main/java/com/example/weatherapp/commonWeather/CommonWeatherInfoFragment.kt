package com.example.weatherapp.commonWeather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.*
import com.example.weatherapp.databinding.FragmentCommonWeatherInfoBinding
import com.example.weatherapp.network.WeatherNetworkRepository


class CommonWeatherInfoFragment : Fragment() {

    private lateinit var commonWeatherFragmentViewModel: CommonWeatherInfoFragmentViewModel

    private lateinit var adapter: WeatherAdapter

    private lateinit var binding: FragmentCommonWeatherInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonWeatherFragmentViewModel = CommonWeatherInfoFragmentViewModel(WeatherNetworkRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = WeatherAdapter()

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_common_weather_info,
            container,
            false
        )

        binding.findCityButton.setOnClickListener {
            commonWeatherFragmentViewModel.onClickFindWeatherInfo(binding.findCityEdit.text.toString())
        }

        setBinding()

        commonWeatherFragmentViewModel.weatherList.observe(viewLifecycleOwner, {
            commonWeatherFragmentViewModel.weatherList.value?.let { adapter.setItems(it) }
        })

        adapter.setOnWeatherClickListener(object: WeatherAdapter.OnWeatherListener {
            override fun onWeatherClick(position: Int) {
                val bundle = Bundle()
                bundle.putParcelable("Weather",
                    commonWeatherFragmentViewModel.weatherList.value?.get(position)
                )
                this@CommonWeatherInfoFragment.findNavController()
                    .navigate(R.id.action_commonWeatherInfo_to_detailedWeatherInfo, bundle)
            }

        })

        return binding.root
    }

    private fun setBinding(){
        binding.commonWeatherInfoViewModel = commonWeatherFragmentViewModel

        binding.weatherRecycler.adapter = adapter

        binding.weatherRecycler.layoutManager = LinearLayoutManager(context)

        binding.lifecycleOwner = this.activity
    }

}