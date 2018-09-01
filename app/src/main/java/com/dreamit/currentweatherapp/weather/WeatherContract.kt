package com.dreamit.currentweatherapp.weather

import com.dreamit.currentweatherapp.weather.model.CityWeather

interface WeatherContract {

    interface View {
        fun showCurrentWeather(cityWeather: CityWeather)

        fun showError(error: String)
    }

    interface Presenter {
        fun getCurrentWeather(cityId: Long)
    }

}