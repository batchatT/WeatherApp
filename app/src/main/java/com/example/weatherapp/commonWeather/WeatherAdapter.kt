package com.example.weatherapp.commonWeather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.WeatherModel
import kotlin.text.StringBuilder

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private lateinit var onWeatherListener: OnWeatherListener

    fun setOnWeatherClickListener(onWeatherListener: OnWeatherListener) {
        this.onWeatherListener = onWeatherListener
    }

    class ViewHolder(itemView: View, listener: OnWeatherListener) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.onWeatherClick(bindingAdapterPosition)
            }
        }

        private val city: TextView = itemView.findViewById(R.id.city_text)
        private val degree: TextView = itemView.findViewById(R.id.degree_text)
        fun bind(item: WeatherModel) {
            city.text = item.weather[0].city
            val temp = StringBuilder(" ")
            if(item.weather[0].mainInfo.degree > 0) {
                temp.append("+")
            }
            Log.d("TestRepo", "bind")
            temp.append(item.weather[0].mainInfo.degree).append("°C")
            degree.text = temp.toString()
        }
    }

    override fun getItemCount(): Int = data.size

    private var data = emptyList<WeatherModel>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = data[position]
            holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_recycler_item, parent, false)
        return ViewHolder(view, onWeatherListener)
    }

    interface OnWeatherListener {
        fun onWeatherClick(position: Int)
    }

    fun setItems(weatherList: List<WeatherModel>) {
        data = weatherList
        notifyDataSetChanged()
    }
}