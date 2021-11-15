package com.example.weatherapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            this.findNavController().navigate(R.id.action_startFragment_to_commonWeatherInfo)
        }, 3000)
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

}