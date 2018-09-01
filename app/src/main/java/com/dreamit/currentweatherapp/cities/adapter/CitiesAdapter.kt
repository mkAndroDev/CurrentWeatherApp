package com.dreamit.currentweatherapp.cities.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.currentweatherapp.R
import com.dreamit.currentweatherapp.cities.model.City
import kotlinx.android.synthetic.main.weather_item.view.*

class CitiesAdapter(
        private val listener: CitiesAdapter.OnCitiesAdapterListener
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private val cities = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item, parent, false)

        return ViewHolder(itemView, cities, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupQuizzes(position)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun resetCities(citiesToShow: List<City>) {
        cities.clear()
        cities.addAll(citiesToShow)
        notifyDataSetChanged()
    }

    class ViewHolder(
            view: View,
            private val cities: List<City>,
            private val listener: OnCitiesAdapterListener
    ) : RecyclerView.ViewHolder(view) {

        private val itemCl = view.cl_city_item
        private val titleTv = view.tv_city_item_name
        private val countryIv = view.tv_city_item_country

        internal fun setupQuizzes(position: Int) {

            val city = cities[position]

            titleTv.text = city.name
            countryIv.text = city.sys?.country
            itemCl.setOnClickListener {
                listener.onCityClicked(city)
            }
        }
    }

    interface OnCitiesAdapterListener {
        fun onCityClicked(city: City)
    }
}