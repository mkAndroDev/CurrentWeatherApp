package com.dreamit.currentweatherapp.data.weathers.remote

import com.dreamit.currentweatherapp.data.weathers.WeathersDataSource
import com.dreamit.currentweatherapp.data.weathers.net.WeatherService
import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable

class RemoteWeathersRepository(
        private val remoteRepository: WeatherService
) : WeathersDataSource {

    override fun getHistoricalWeathers(): Observable<List<Weather>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeather(city: String): Observable<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveWeather(weather: Weather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}