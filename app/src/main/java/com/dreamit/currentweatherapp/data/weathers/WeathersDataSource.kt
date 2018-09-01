package com.dreamit.currentweatherapp.data.weathers

import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable

interface WeathersDataSource {

    fun getHistoricalWeathers(): Observable<List<Weather>>

    fun getWeather(city: String): Observable<Weather>

    fun saveWeather(weather: Weather)

}