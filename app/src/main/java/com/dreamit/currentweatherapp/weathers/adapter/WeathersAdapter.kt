package com.dreamit.currentweatherapp.weathers.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.currentweatherapp.R
import com.dreamit.currentweatherapp.weathers.model.Weather
import kotlinx.android.synthetic.main.weather_item.view.*

class WeathersAdapter(
        private val listener: WeathersAdapter.OnWeatherAdapterListener
) : RecyclerView.Adapter<WeathersAdapter.ViewHolder>() {

    private var weathers: List<Weather> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item, parent, false)

        return ViewHolder(itemView, weathers, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupQuizzes(position)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    fun resetQuizzes(weathersToShow: List<Weather>) {
        weathers = weathersToShow
    }

    class ViewHolder(
            view: View,
            private val weathers: List<Weather>,
            private val listener: OnWeatherAdapterListener
    ) : RecyclerView.ViewHolder(view) {

        private val itemCl = view.cl_weather_item
        private val titleTv = view.tv_weather_item_title
        private val descriptionIv = view.tv_weather_item_description
        private val tempTv = view.tv_weather_item_temp

        internal fun setupQuizzes(position: Int) {

            val weather = weathers[position]

            titleTv.text = weather.name

            itemCl.setOnClickListener {
                listener.onWeatherClicked(weather)
            }
        }
    }

    interface OnWeatherAdapterListener {
        fun onWeatherClicked(weather: Weather)
    }
}