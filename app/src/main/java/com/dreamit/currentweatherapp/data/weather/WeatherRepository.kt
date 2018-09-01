package com.dreamit.currentweatherapp.data.weather

import com.dreamit.currentweatherapp.weather.model.CityWeather
import com.dreamit.currentweatherapp.data.weather.local.LocalWeatherRepository
import com.dreamit.currentweatherapp.data.weather.remote.RemoteWeatherRepository
import io.reactivex.Observable

class WeatherRepository(
        private val localRepository: LocalWeatherRepository,
        private val remoteRepository: RemoteWeatherRepository
) : WeatherDataSource {

    override fun getWeather(city: String): Observable<CityWeather> {
        return Observable.concat(
                remoteRepository.getWeather(city),
                localRepository.getWeather(city)
        )
    }

    override fun saveWeather(cityWeather: CityWeather) {
        localRepository.saveWeather(cityWeather)
    }
}