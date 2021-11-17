package com.example.weatherapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.text.StringBuilder

class WeatherAdapter(weatherList: List<WeatherInfo>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private lateinit var onWeatherListener : OnWeatherListener

    fun setOnWeatherClickListener(onWeatherListener: OnWeatherListener){
        this.onWeatherListener = onWeatherListener
    }

    class ViewHolder(itemView: View, listener : OnWeatherListener) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.setOnClickListener{
                listener.onWeatherClick(bindingAdapterPosition)
            }
        }

        private val city: TextView = itemView.findViewById(R.id.city_text)
        private val degree: TextView = itemView.findViewById(R.id.degree_text)
        fun bind (item:WeatherInfo){
            city.text = item.city
            val temp = StringBuilder(" ")
            if(item.degree > 0) {
                temp.append("+")
            }
            temp.append(item.degree).append("°C")
            degree.text = temp.toString()
        }
    }

    var data = weatherList

    override fun getItemCount() :Int= data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_recycler_item, parent, false)
        return ViewHolder(view, onWeatherListener)
    }

    interface OnWeatherListener{
        fun onWeatherClick(position:Int)
    }

    fun filter(filteredList: ArrayList<WeatherInfo>){
        data = filteredList
        notifyDataSetChanged()
    }

}