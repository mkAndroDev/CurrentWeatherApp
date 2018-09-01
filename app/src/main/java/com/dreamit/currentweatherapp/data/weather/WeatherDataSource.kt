package com.dreamit.currentweatherapp.data.weather

import com.dreamit.currentweatherapp.weather.model.CityWeather
import io.reactivex.Observable

interface WeatherDataSource {

    fun getWeather(city: String): Observable<CityWeather>

    fun saveWeather(cityWeather: CityWeather)
}