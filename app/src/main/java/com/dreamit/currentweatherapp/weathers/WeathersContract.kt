package com.dreamit.currentweatherapp.weathers

import com.dreamit.currentweatherapp.weathers.model.Weather

interface WeathersContract {

    interface View {
        fun showLastSearches(weathers: List<Weather>)

        fun showCurrentWeather(weather: Weather)

        fun showError(error: String)
    }

    interface Presenter {
        fun getHistoricalWeathers()

        fun getCurrentWeather(cityName: String)
    }

}