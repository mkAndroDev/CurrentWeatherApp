package com.dreamit.currentweatherapp.data.weather.remote

import android.util.Log
import com.dreamit.currentweatherapp.weather.model.CityWeather
import com.dreamit.currentweatherapp.data.weather.WeatherDataSource
import com.dreamit.currentweatherapp.net.APP_ID
import com.dreamit.currentweatherapp.net.WeatherService
import io.reactivex.Observable

class RemoteWeatherRepository(
        private val remoteRepository: WeatherService
) : WeatherDataSource {

    override fun getWeather(city: String): Observable<CityWeather> =
            remoteRepository.getWeather(city, APP_ID)

    override fun saveWeather(cityWeather: CityWeather) {
        Log.e(TAG, "saveWeather -> Not implemented!")
    }

    companion object {
        private val TAG = RemoteWeatherRepository::class.java.simpleName
    }
}