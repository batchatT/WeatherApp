package com.example.weatherapp.commonWeather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.*
import com.example.weatherapp.databinding.FragmentCommonWeatherInfoBinding
import com.example.weatherapp.utils.WEATHER_INFO_KEY
import javax.inject.Inject

class CommonWeatherInfoFragment : Fragment() {

    @Inject
    lateinit var commonWeatherFragmentViewModelFactory: CommonWeatherInfoViewModelFactory

    private val commonWeatherFragmentViewModel: CommonWeatherInfoFragmentViewModel by viewModels {
        commonWeatherFragmentViewModelFactory
    }

    private val adapter by lazy {
        WeatherAdapter(mutableListOf()).apply {
            setOnWeatherClickListener(object : WeatherAdapter.OnWeatherListener {
                override fun onWeatherClick(weather: WeatherModel) {
                    val bundle = Bundle()
                    bundle.putParcelable(
                        WEATHER_INFO_KEY,
                        weather
                    )
                    this@CommonWeatherInfoFragment.findNavController()
                        .navigate(R.id.action_commonWeatherInfo_to_detailedWeatherInfo, bundle)
                }
            })
        }
    }

    private lateinit var binding: FragmentCommonWeatherInfoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as WeatherApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_common_weather_info,
            container,
            false
        )

        setBinding()

        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        commonWeatherFragmentViewModel.searchByNameWeatherLiveData.observe(viewLifecycleOwner, {
            adapter.updateItem(it)
        })

        commonWeatherFragmentViewModel.errorEventFromResponse.observe(viewLifecycleOwner, {
            if (it) {
                ErrorFromResponseDialog().show(childFragmentManager, ErrorFromResponseDialog.TAG)
                commonWeatherFragmentViewModel.errorIsShown()
            }
        })
    }


    private fun setBinding() {
        binding.commonWeatherInfoViewModel = commonWeatherFragmentViewModel

        binding.weatherRecycler.adapter = adapter

        binding.weatherRecycler.layoutManager = LinearLayoutManager(context)

        binding.lifecycleOwner = this.activity

        binding.findCityButton.setOnClickListener {
            commonWeatherFragmentViewModel.onClickFindWeatherInfo(binding.findCityEdit.text.toString())
        }
    }

}