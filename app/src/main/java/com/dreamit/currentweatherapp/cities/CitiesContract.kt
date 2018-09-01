package com.dreamit.currentweatherapp.cities

import com.dreamit.currentweatherapp.cities.model.City

interface CitiesContract {

    interface View {
        fun showCities(cities: List<City>)

        fun showError(error: String)
    }

    interface Presenter {
        fun getCities(cityName: String)

        fun getHistoricalCities()

        fun saveCity(city: City)
    }

}