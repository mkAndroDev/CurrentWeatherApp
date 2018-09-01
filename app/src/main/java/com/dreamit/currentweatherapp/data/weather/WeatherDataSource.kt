package com.dreamit.currentweatherapp.data.weather

import com.dreamit.currentweatherapp.weather.model.CityWeather
import io.reactivex.Observable

interface WeatherDataSource {

    fun getWeather(cityId: Long): Observable<CityWeather>

    fun saveWeather(cityWeather: CityWeather)
}