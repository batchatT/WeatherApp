package com.example.weatherapp.commonWeather

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.databinding.WeatherRecyclerItemBinding


class WeatherAdapter(private var data: MutableList<WeatherModel>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private lateinit var onWeatherListener: OnWeatherListener

    fun setOnWeatherClickListener(onWeatherListener: OnWeatherListener) {
        this.onWeatherListener = onWeatherListener
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onWeatherListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherRecyclerItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    interface OnWeatherListener {
        fun onWeatherClick(weather: WeatherModel)
    }

    fun updateItem(weather: WeatherModel) {
        data.add(weather)
        notifyItemChanged(data.size - 1)
    }

    class ViewHolder(private val binding: WeatherRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherModel, listener: OnWeatherListener) {

            itemView.setOnClickListener {
                listener.onWeatherClick(item)
            }

            binding.city = item.weather[0].city

            val temp = StringBuilder(" ")
            if (item.weather[0].mainInfo.degree > 0) {
                temp.append("+")
            }
            Log.d("TestRepo", "bind")
            temp.append(item.weather[0].mainInfo.degree).append("°C")
            binding.degree = temp.toString()
        }
    }
}